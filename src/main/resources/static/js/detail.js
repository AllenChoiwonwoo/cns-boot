$(document).ready(function(){
	
	var token;
	if(document.cookie.includes("accesstoken")) {
		
		token = document.cookie.split('token=')[1];
	}

	var postId = $('#detail_post_id').attr("value");
	postId = postId.replace(/,/g, "");
	console.log("postId - " + postId);
	
	$.ajax({
		beforeSend: function(xhr){
			xhr.setRequestHeader('accesstoken', token);
        },
        url: "/post/" + postId
    }).then(function(data) {
       console.log(data);
       $('#detail_title').text(data.data.title);
       $('#detail_user').text(data.data.user.username);
       $('#detail_date').text(data.data.createdAt);
       $('#detail_content').text(data.data.content);
    }, function(err) {
    	console.log(err.responseJSON);
    });
	
	
	$('#detail_delete_btn').click(function(){
		var postId = $('#detail_post_id').attr("value");
		postId = postId.replace(/,/g, "");
		console.log("delete button click! - " + postId);
		$.ajax({
			beforeSend: function(xhr){
			xhr.setRequestHeader('accesstoken', token);
        },
	        url: "/post/"+postId,
	        method: "DELETE"
	    }).then(function(data) {
			console.log("ok ", data);
			if(data.code >= 500){
				alert(data.data);
			}else{
				window.location.href = '/';
			}
	    	
	    }, function(err) {
			console.log(data);
	    	alert(err.responseJSON);
	    });
	});
	
	$('#modify_post_btn').click(function(){
		var postId = $('#detail_post_id').attr("value");
		var title = $('#modify_title_text').val();
		var content = $('#modify_content_text').val();
		
		console.log(postId);
		console.log(title);
		console.log(content);
		
		var param = {
			id: postId,
			title: title,
			content: content
		}
		
		$.ajax({
			beforeSend: function(xhr){
			xhr.setRequestHeader('accesstoken', token);
        },
	        url: "/post",
	        method: "PUT",
	        dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(param)
	    }).then(function(data) {
			console.log("success ",data);
	    	if(data.code >= 500){
				alert(data.data);
			}else{
				window.location.href = '/';
			}
	    }, function(err) {
			console.log("error ",data);
	    	alert(err.responseJSON);
	    });
	});
	
	$('#create_comment_btn').click(function(){
		var postId = $('#detail_post_id').attr("value");
		var user = $('#comment_user_text').val();
		var comment = $('#comment_text').val();
		
		console.log(postId);
		console.log(user);
		console.log(comment);
		
		var param = {
				postId: postId,
				user: user,
				comment: comment
		}
		
		$.ajax({
			beforeSend: function(xhr){
			xhr.setRequestHeader('accesstoken', token);
        },
	        url: "/comment",
	        method: "POST",
	        dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(param)
	    }).then(function(data) {
			console.log("success ",data);
	    	window.location.href = '/post/detail/'+postId;
	    }, function(err) {
			console.log("error ",data);
	    	alert(err.responseJSON);
	    });
	});
});