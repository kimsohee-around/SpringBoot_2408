const save = () =>{

    //1. 사용자 입력값 가져오기
    const name = document.getElementById('name').value
    const email = document.getElementById('email').value
    const tel = document.getElementById('tel').value
    const password = document.getElementById('password').value

    //2. 요청 보낼 자바스크립트 객체 만들기
    const jsObj = {
        name: name,         //속성이름: 값
        email: email,
        tel: tel,
        password: password
    }
    console.log('오브젝트:',jsObj)
    console.log('json:',JSON.stringify(jsObj))

    //3. 요청 보낼 메소드 방식, 헤더, 본문 저장 => 객체
    const options = {
        method: 'POST',
        headers: {   // 프로퍼티 값은 문자열
              'Content-Type': 'application/json;charset=UTF-8'
            },
        body: JSON.stringify(jsObj)
    }  //요청 본문은 json 형식으로 직렬화(문자열)

    //4. 요청 보내기
    fetch('/members',options)
        .then(response => {
            if(!response.ok) {    //response.status !== 200
                throw new Error('사용자 등록 오류 status =' + response.status)
            }
            return response.json()
        })
        .then( obj => {
            alert('회원 번호 : ' + obj.mem_idx + ' 로 등록되었습니다.')
            init()
        })
        .catch(error => {
            //요청이 거부되었을 대 실행하는 구문.
            console.error('Save Error:', error)
        })
}       // save 함수 정의 끝
// 등록버튼 클릭하면 save 함수 실행하기
document.getElementById('save').addEventListener('click',save)

function init(){
    document.getElementById('name').value = '';
    document.getElementById('email').value =''
    document.getElementById('tel').value=''
    document.getElementById('password').value=''

}




