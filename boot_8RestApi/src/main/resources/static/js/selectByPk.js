// mem_idx 컬럼(PK) 조회
const selectByPk = () => {
    const pk
        = document.getElementById('memIdx')
    if(!pk.value){
        alert('조회할 회원번호를 입력하세요.')
        pk.focus()
        return false;   //함수 종료
    }

    const options = { method: 'GET'}
    fetch(`/members/${pk.value}`,options)
        .then(response => {
            if(response.status === 204){
                alert('조회된 데이터가 없습니다.')
                pk.focus()
            }else if(response.status === 200){
                return response.json()
            }

        })
        .then(obj => {
            console.log('obj=',obj)
            if(obj) {
                alert('조회되었습니다.')
                createRow(obj)
       //table - tbody 태그안의 tr 을 생성. 값을 출력하는 함수
            }

        })
        .catch(error => {
            console.error('Fetch Error:',error)
        })

}  // 함수 끝

document.getElementById('selectPk')
    .addEventListener('click',selectByPk)







