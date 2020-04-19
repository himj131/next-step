String.prototype.format = function() {
  var args = arguments;
  return this.replace(/{(\d+)}/g, function (match, number) {
    return typeof args[number] != 'undefined'
        ? args[number]
        : match
        ;
  });
};

$(".answerWrite input[type=submit]").click(addAnswer);
// $(".qna-comment .link-delete-article").click(deleteAnswer);
$(".qna-comment").on("click", ".form-delete", deleteAnswer);

function deleteAnswer(e) {
  e.preventDefault();
  // const queryString = $(".form-delete").closest("form").serialize();
  // $.ajax({
  //   type: 'post',
  //   url: '/api/qna/deleteAnswer',
  //   data: queryString,
  //   dataType: 'json',
  //   error: onDeleteError,
  //   success: onDeleteSuccess,
  // });

  var deleteBtn = $(this);
  var queryString = deleteBtn.closest("form").serialize();
  $.ajax({
    type: 'post',
    url: '/api/qna/deleteAnswer',
    data: queryString,
    dataType: 'json',
    error: function () {
      alert("error");
    },
    success: function(json, status){
      if(json.status) {
        deleteBtn.closest('article').remove();
      }
    }
  });
}

function addAnswer(e) {
  e.preventDefault();
  const queryString = $("form[name=answer]").serialize();

  $.ajax({
    type: 'post',
    url: '/api/qna/addAnswer',
    data: queryString,
    dataType: 'json',
    error: onError,
    success: onSuccess,
  });
}

function onError() {

}

function onSuccess(json, status) {
  const answerTemplate = $("#answerTemplate").html();
  const template = answerTemplate.format(json.writer, new Date(json.createdDate), json.contents, json.answerId, json.answerId);
  $(".qna-comment-slipp-articles").prepend(template);
}
