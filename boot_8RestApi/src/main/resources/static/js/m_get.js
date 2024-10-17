const selectPk = () => {
    const pk = document.getElementById('memIdx')
    if(!pk.value){
        alert('조회할 회원 번호를 입력하세요.')
        pk.focus()
        return false;
    }

    const options={
        method: 'GET'
    }

    fetch(`/members/${pk.value}`, options)
        .then(response => {
            console.log('response -',response)
            if(response.status === 204){
                alert('조회된 데이터가 없습니다.')
                pk.focus()
                // throw new Error('사용자 조회 오류 - ' + response.status)
            }else

            if(!response.ok){
                throw new Error('사용자 조회 오류 - ' + response.status)
            }

            return response.json()
        })
        .then(obj => {
            console.log('obj=',obj)
            createRow(obj)
        })
        // .then(() => alert('조회 되었습니다.'))
        .catch(error =>  console.error('Fetch error:', error))
}

document.getElementById('selectPk').addEventListener('click',selectPk)

function createRow(obj){
    const tr = document.createElement('tr')
    tr.innerHTML=`
            <td>${obj.mem_idx}</td>
            <td>${obj.name}</td>
            <td><input id="m_email" value="${obj.email}" disabled></td>
            <td><input id="m_tel" value="${obj.tel}" disabled></td>
            <td>
            <!-- 조회 결과에 따라 동적으로 추가되는 요소
                 이 요소에 이벤트 설정하기
            -->
                <a class="btn">
                    <i class="fas fa-edit" data-num="${obj.mem_idx}"></i>
                </a>
                <a class="btn">
                    <i class="fas fa-trash" data-num="${obj.mem_idx}"></i>
                <a>
            </td>
    `
        tr.addEventListener('click',function (e) {
            e.preventDefault()
            const icons = document.querySelectorAll('i')
            if(e.target.tagName !== 'I') return false

            if(e.target.classList.contains('fa-edit')) {        //편집 아이콘 클릭
                alert('수정합니다.')
                document.querySelectorAll('input').forEach(ele =>{
                    ele.disabled=false
                })
                e.target.classList.replace('fa-edit','fa-save')
                icons[1].classList.replace('fa-trash','fa-times')
            }else if (e.target.classList.contains('fa-trash')){   //휴지통 아이콘 클릭
                let yn = confirm('회원 삭제 합니다.')
                if(yn) {
                    remove(obj.mem_idx)
                    document.querySelector('tbody').innerHTML=''
                }

            }else if(e.target.classList.contains('fa-save')){   //저장 아이콘 클릭
                let yn = confirm('변경 내용 저장 합니다.')
                const email = document.getElementById('m_email').value
                const tel = document.getElementById('m_tel').value
                if(yn) update(email, tel, obj.mem_idx)

                document.querySelectorAll('#list input').forEach(ele =>{
                    ele.disabled=true
                })
                e.target.classList.replace('fa-save','fa-edit')
                icons[1].classList.replace('fa-times','fa-trash')
            }else if(e.target.classList.contains('fa-times')){    //취소 아이콘 클릭
                alert('수정을 취소합니다.')
                selectPk(obj.mem_idx)
                document.querySelectorAll('#list input').forEach(ele =>{
                    ele.disabled=true
                })
                e.target.classList.replace('fa-times','fa-trash')
                icons[0].classList.replace('fa-save','fa-edit')
            }


        })

        document.querySelector('tbody').innerHTML=''
        document.querySelector('tbody').appendChild(tr)
}

function clear() {
    document.getElementById('memIdx').value=''
    document.querySelector('tbody').innerHTML=''
}

document.querySelector('#close').addEventListener('click',clear)
document.querySelector('#closex').addEventListener('click',clear)

