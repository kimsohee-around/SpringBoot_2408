const testKakao = () =>{

    const xhr = new XMLHttpRequest();
    //파라미터 설정 : 카카오 문서 참고
    let query = document.getElementById('query').value;
    let sort = document.getElementById('accuracy').checked;  //boolean
    let page = document.getElementById('page').value;
    let size = document.getElementById('size').value;
    if(sort)
        sort = 'accuracy';
    else
        sort = 'recency';

    let params = `?query=${query}&sort=${sort}&page=${page}&size=${size}`
    //요청 url
    xhr.open('GET',`https://dapi.kakao.com/v2/search/vclip${params}`);     //메소드,url
    xhr.setRequestHeader('Authorization','KakaoAK 040548049c66599f6c14e5b2374620c0');
    xhr.send();       //요청 전송
    let data;
    //응답 처리. 콜백함수
    xhr.onload = ()=>{
        if(xhr.status === 200) {
            alert('카카오 검색 응답을 받았습니다.');
            let resp = xhr.response   //문자열
            console.log(resp)
            let respObj = JSON.parse(resp)
            console.log(respObj)
            data = respObj.documents
            console.log('onload 함수 안 : ',data)
            //*****
            document.getElementById('list').innerHTML=''
            data.forEach( (item) => {
                let resultUL = createVideoItem(item)
                document.getElementById('list').appendChild(resultUL);
            })

        }
    } //onload end


    // 응답 받은 respObj.documents 배열입니다. 배열에 대해 함수
    //  createVideoItem 를 반복해서 화면에 배열 크기만큼 ul 를 추가합니다.
    //  ==> div#list 요소에 appendChild 를 하세요. ***** 위치에 코딩.
    console.log('onload 함수 밖 : ',data)  // undefied


} //testKakao end


function createVideoItem(video) {
    const ul = document.createElement('ul');
    ul.innerHTML = `
      <li>${video.author}</li>
      <li>${video.title}</li>
      <li>${video.play_time} 초</li>
      <li>${video.datetime}</li>
      <li>
        <li>
        <a href="${video.url}" target="_blank">
          <img src="${video.thumbnail}" width="200px">
        </a>
      </li>
      </li>
    `;
    return ul;
}


document.getElementById('search')
        .addEventListener('click',testKakao);
