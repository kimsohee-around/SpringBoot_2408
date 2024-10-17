//1. 입력값 가져오기
const save = () => {
    const name = document.querySelector('#name').value
    const password = document.querySelector('#password').value
    const email = document.querySelector('#email').value
    const tel = document.querySelector('#tel').value
//2. 입력값으로 자바스크립트 객체 생성(자바스크립트 객체는 미리 타입을 정의하지 않고 사용할수 있습니다.)
    const jsObj = {
        name: name,
        password: password,
        email: email,
        tel: tel
    }
    console.log(jsObj)
//3.자바스크립트 객체를 json 전송을 위해 직렬화 (문자열로 변환)
    const jsStr = JSON.stringify(jsObj)
    const options =  {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=UTF-8'
        },
        body: jsStr
    }

    fetch('/members', options)
        .then(response => {
            console.log('response',response)
            if(!response.ok){
                throw new Error('사용자 등록 오류 : status = '+ response.status )
            }
                // console.log('response=', response)
                // console.log('response.json()', response.json())
                // console.log('response.json().Object', response.json())
                return response.json()
                })
        .then(obj => {

            console.log('data=',obj)
            alert('회원번호 : ' + obj.mem_idx + ' 로 등록되었습니다.')

        }).then( () => init())
        .catch(error => {
            console.error('Fetch error:', error);
        });


}
document.getElementById('save').addEventListener('click',save)

function init(){
        document.querySelector('#name').value=''
        document.querySelector('#email').value=''
        document.querySelector('#tel').value=''
        document.querySelector('#password').value=''
}
