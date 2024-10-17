const replyUl = document.getElementById("replyList")
console.log(replyUl)
const mref = document.getElementById("mref").value
console.log(mref)



// 페이지 렌더링 후 바로 실행되는 함수 입니다.
getReplyList()   
// 댓글 등록 및 삭제 후에 댓글 목록을 다시 요청해야합니다. => 함수로 작성
function getReplyList() {
    const options = {
        method: 'GET'
    }
    fetch(`/api/comments/${mref}`, options)
        .then((response) => {
            console.log('response:', response)
            if (!response.ok) {
                throw new Error('댓글 조회 오류 :' + response.status)
            }
            return response.json()     //응답 body 를 js 객체로 변환
        })
        .then((obj) => {
            console.log('data : ', obj, typeof obj)
            printReplyList(obj)
        })
        .catch((error) => {
            console.error('error:', error)
        })
}

function printReplyList(list) {
    let str = ''
    if(list && list.length > 0){
        list.forEach((dto) => {
        str+= ` <li class="list-group-item d-flex">
               <span class="col-5 myfc-1">${dto.writer}</span>
               <span class="col-6">${dto.createdAt}</span>
               `
        if(dto.writer === username) {
           str+=`<span class="col-1">
                   <i class="fas fa-trash" data-num="${dto.idx}"></i>
                 </span>`
        }
        str+=  `</li>
               <li class="list-group-item d-flex">
                 <textarea class="form-control myfs-9" disabled>${dto.content}</textarea>
               </li>`

        })  //for 끝
        replyUl.innerHTML = str
        document.querySelectorAll('i').forEach((btn) =>{
            btn.addEventListener('click',(e)=>{
                // alert(e.target.tagName + ":"
                //         + e.target.getAttribute('data-num'))
                //삭제 요청 하는 fetch 실행하기
                const idx = e.target.getAttribute('data-num')
                const yn = confirm("댓글 삭제하시겠습니까?")
                removeReply(idx)
            })
        })
        document.querySelectorAll('textarea')
            .forEach(ele =>{
            ele.style.height='auto'
            ele.style.height = ele.scrollHeight + 'px'
                ele.style.overflow='hidden'   //스크롤바 숨기기
        })
    } //if 끝
}

function removeReply(idx){
    const options = {
        method: 'DELETE'
    }

    fetch(`/api/comments/${idx}`,options)
        .then(response => {
            if(response.status === 400){
                alert('삭제할 댓글이 없습니다.')
            }else if(response.status === 200) {
                alert('댓글 삭제 했습니다.')
                getReplyList()
            }
        })
        .catch(error => console.error('Patch Error:',error))
}

const saveReply = () => {
    const mref = document.querySelector('#mref').value
    const writer = document.querySelector('#writer').value
    const content = document.querySelector('#content').value


    console.log("username:",username)
    //자바스크립트는 null,undefined, 0, '',false  는 모두 거짓으로 처리됩니다.

    if(!username) {     //username은 '' 또는 '이메일주소'
        alert("로그인이 필요합니다.")
        const yn = confirm("로그인 하시겠습니까?")
        if(yn) location.href="/login"
        return;
    }
    if(!content || !content.trim().length>0) {
        alert("댓글 내용을 입력하세요.")
        return;
    }

    const jsObj = {
        mref: mref,
        writer: writer,
        content: content,
    }
    console.log("jsObj:",jsObj)
    const options =  {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=UTF-8'
        },
        body: JSON.stringify(jsObj)
    }

    fetch('/api/comments', options)
        .then(response => {
            if(!response.ok && response.status !== 400){
                throw new Error('사용자 등록 오류 : status = '+ response.status )
            }
            return response.json()
        })
        .then(obj => {
            if (obj.errors !=null){
                //유효성 바인딩 오류 메시지는 서버가 errors 에 저장함.
                console.log(obj.errors)
                document.getElementById('errMsg').innerHTML=obj.errors.content
                return 0;   //다음 then, catch 실행하지 않고 종료하기
            }else {
                alert('회원번호 : ' + obj.idx + ' 로 등록되었습니다.')
                document.getElementById('content').value = ''
                document.getElementById('errMsg').innerHTML=''
            }
        })
        .then(() =>  getReplyList())
        .catch(error => {   //fetch 요청이 실패하거나 throw new Error() 일 때
            console.error('댓글 등록 error:', error);
        });
}
//댓글 저장 버튼
document.getElementById('btnSave').addEventListener('click',saveReply)


// const btn =document.createElement("i")
// btn.classList.add('fas','fa-trash')
// btn.setAttribute("data-num","${dto.idx}")
// btn.addEventListener('click',(e)=>{
//             alert(e.target.name + e.target.getAttribute('data-num'))
// })




