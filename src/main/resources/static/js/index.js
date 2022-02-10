$("#btnComment").click(async function (e) {
    let comment_content = document.getElementById("comment").value;
    let postId = window.location.pathname.split("/");
    postId = postId[postId.length - 1];
    if (comment_content != 'undefined' && comment_content.length > 0) {
        let xhr = await new XMLHttpRequest();
        xhr.open("POST", "https://nvkha-blog.herokuapp.com/api/v1/comments" + "?postId=" + postId, true);
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.send(JSON.stringify({
            content: comment_content
        }));
        window.location.reload();
    } else {
        alert("Comment can't empty");
    }
});


$("#btnSignup").click(function (e) {
    e.preventDefault();
    let name = document.getElementById("username").value;
    let pass = document.getElementById("password").value;
    let xhr = new XMLHttpRequest();
    // Example request options
    fetch("https://nvkha-blog.herokuapp.com/api/v1/registration", {
        method: 'post', // Default is 'get'
        body: JSON.stringify({
            username: name,
            password: pass
        }),
        mode: 'cors',
        headers: new Headers({
            'Content-Type': 'application/json'
        })
    })
        .then(response => response.text())
        .then(json => {
            if(json.toString() == 'ok') {
                window.location.assign("https://nvkha-blog.herokuapp.com/signup?isSuccess=true");
            }
            else {
                window.location.assign("https://nvkha-blog.herokuapp.com/signup?isSuccess=false");
            }
        })
});

$("#btnAddPost").click(function (e) {
    e.preventDefault();
    let title = document.getElementById("title").value;
    let content = document.getElementById("content").value;
    if (title != 'undefined' && title.length > 0 &&
        content != 'undefined' && content.length > 0) {
        let xhr = new XMLHttpRequest();
        xhr.open("POST", "https://nvkha-blog.herokuapp.com/api/v1/posts", true);
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.send(JSON.stringify({
            title: title,
            content: content
        }));
        window.location.assign("https://nvkha-blog.herokuapp.com/admin/manage-posts?message=Add post success" +
        "&type=success");
    } else {
        alert("Title or content can't empty");
    }
});


$("#btnSave").click(function (e) {
    e.preventDefault();
    let title = document.getElementById("title").value;
    let content = document.getElementById("content").value;
    let arr = window.location.pathname.split("/")
    let id = arr[arr.length - 1];
    if (title != 'undefined' && title.length > 0 &&
        content != 'undefined' && content.length > 0) {
        let xhr = new XMLHttpRequest();
        xhr.open("PUT", "https://nvkha-blog.herokuapp.com/api/v1/posts/" + id +
            "?title=" + title + "&content=" + content, true);
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.send();
        window.location.assign("https://nvkha-blog.herokuapp.com/admin/manage-posts/edit/" +
            id + "?isSuccess=true");
    } else {
        alert("Title or content can't empty");
    }
});