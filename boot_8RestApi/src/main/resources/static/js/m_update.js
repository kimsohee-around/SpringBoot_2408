const update = (email, tel, memIdx)=>{

    const options = {
        method: 'PATCH',
        headers: {'Content-Type':'application/json;charset=UTF-8'},
        body: JSON.stringify({email:email, tel:tel, mem_idx:memIdx})
    }

    fetch(`/members`,options)
        .then(response => {
           return response.json()
        })
        .then(obj => {
            if(obj.rows === 1){
                alert('수정되었습니다.')
                createRow(obj.data)
            }else if(obj.rows === 0){
                alert('변경사항이 없습니다.')
                createRow(obj.data)
            }
        })
        .catch(error => console.error('Patch Error:',error))
}

const remove = (memIdx)=>{

    const options = {
        method: 'DELETE'
    }

    fetch(`/members/${memIdx}`,options)
        .then(response => {
            if(response.status === 400){
                alert('삭제할 회원번호가 없습니다.')
            }else if(response.status === 200) {
                alert('회원 삭제 했습니다.')

            }
        })
        .catch(error => console.error('Patch Error:',error))
}