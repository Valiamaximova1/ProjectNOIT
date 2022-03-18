const placeId = document.getElementById('placeId').value

const csrfHeaderName = document.head.querySelector('[name="_csrf_header"]').content;
const csrfHeaderValue = document.head.querySelector('[name="_csrf"]').content;


const commentsCtnr = document.getElementById('commentCtnr')

const commentForm = document.getElementById('commentForm')
commentForm.addEventListener("submit", handleCommentSubmit)

const allComments = []

const displayComments = (comments) => {
    commentsCtnr.innerHTML = comments.map(
        (c) => {
            return asComment(c)
        }
    ).join('')
}

async function handleCommentSubmit(event) {
    event.preventDefault();

    const form = event.currentTarget;
    const url = form.action;
    const formData = new FormData(form);

    try {
        const responseData = await postFormDataAsJson({url, formData});

        commentsCtnr.insertAdjacentHTML("afterbegin", asComment(responseData));

        form.reset();
    } catch (error) {

        let errorObj = JSON.parse(error.message);

        if (errorObj.fieldWithErrors) {
            errorObj.fieldWithErrors.forEach(
                e => {
                    let elementWithError = document.getElementById(e);
                    if (elementWithError) {
                        elementWithError.classList.add("is-invalid");
                    }
                }
            )
        }

    }
    console.log('going to submit a comment!')
}

async function postFormDataAsJson({url, formData}) {

    const plainFormData = Object.fromEntries(formData.entries());
    const formDataAsJSONString = JSON.stringify(plainFormData);

    const fetchOptions = {
        method: "POST",
        headers: {
            [csrfHeaderName]: csrfHeaderValue,
            "Content-Type": "application/json",
            "Accept": "application/json"
        },
        body: formDataAsJSONString
    }

    const response = await fetch(url, fetchOptions);

    if (!response.ok) {
        const errorMessage = await response.text();
        throw new Error(errorMessage);
    }

    return response.json();
}

function asComment(c) {
    let commentHtml = ` <div class="my-4 border rounded p-2">`

    commentHtml += `<div class="d-flex w-100 justify-content-between">`
    commentHtml += `<h5 class="mb-1" id="commentCntr-${c.commentId}"><span class="text-secondary fw-bold me-2"></span>${c.user}</h5>`
    commentHtml += `<small class="text-muted">${c.created.toString().replaceAll('T', ' ').slice(0, 19)}</small>`
    commentHtml += `</div>`
    commentHtml += `<p class="mb-1">${c.message}</p>`
    commentHtml += `</div>`
    // let commentHtml = `<div class="col-md-12" id="commentCntr-${c.commentId}">`
    //
    // commentHtml += `<h4 class="form-label">Потребител: ${c.user}</h4> <p class="form-label">Време на качване: (${c.created.toString().replaceAll('T', ' ').slice(0,19)})</p><br/>`
    // commentHtml += `<p >${c.message}</p>`
    // commentHtml += `</div>`

    return commentHtml
}

fetch(`http://localhost:8000/api/${placeId}/comments`).then(response => response.json()).then(data => {
    for (let comment of data) {
        allComments.push(comment)
    }
    displayComments(allComments)
    console.log("wprking")
})

