var postsArray = new Array();
var videosArray = new Array();
var doubtsArray = new Array();
var allPostArray = new Array();
var i = 0;
var temp = new Array();
var postsHtml="";
var videosHtml="";
var doubtsHtml="";
var allHtml="";

function getMethod(pUrl,storeArray){
		
		let data = $.parseJSON($.ajax({
	        url:  pUrl,
	        dataType: "json", 
	        async: false
	    }).responseText);
		
		if(storeArray==2){
			postsArray = new Array();
			postsArray = [...data]; 
		}
		
		if(storeArray==3){
			videosArray = new Array();
			videosArray = [...data]; 
		}
		
		if(storeArray==4){
			doubtsArray = new Array();
			doubtsArray = [...data]; 
		}
		
}

/*function addtoArray(temp){
	allPostArray.push(...temp);
	console.log("All Post length : ",allPostArray.length);
}

async function getUsers() {
    let url = "./selectquery?rnum=2&&cid="+cid;
    try {
        let res = await fetch(url);
        return await res.json();
    } catch (error) {
        console.log(error);
    }
}

async function renderUsers() {
    let users = await getUsers();
	for(i=0;i<users.length;i++){
		doubtsArray.push(users[i]);
		allPostArray.push(users[i]);
	}
}

async function getPosts() {
    let url = "./selectquery?rnum=3&&cid="+cid;
    try {
        let res = await fetch(url);
        return await res.json();
    } catch (error) {
        console.log(error);
    }
}

async function renderPosts() {
    let users = await getPosts();
	for(i=0;i<users.length;i++){
		postsArray.push(users[i]);
		allPostArray.push(users[i]);
	}
}

async function getVideos() {
    let url = "./selectquery?rnum=4&&cid="+cid;
    try {
        let res = await fetch(url);
        return await res.json();
    } catch (error) {
        console.log(error);
    }
}

var finalVideosHtml="";

async function renderVideos() {
    let users = await getVideos();
	for(i=0;i<users.length;i++){
		videosArray.push(users[i]);
		allPostArray.push(users[i]);
	}
	//assignVideos(videosHtml);
	dataConfirmation();
}

function assignVideos(texts){
	finalVideosHtml = new String(texts);;
}*/

var totalHtml = "";

function dataConfirmation(){
	allPostArray = allPostArray.concat(postsArray,videosArray,doubtsArray);
	allPostArray.sort(function(a, b){return new Date(b["pdt"]) - new Date(a["pdt"]) });
	console.log("Data : "+allPostArray);
	temp.push(...allPostArray);
	totalHtml = "";
	for(i=0;i<allPostArray.length;i++){
		if(allPostArray[i]["gtype"]=="P"){
			postsHtml += returnPost(allPostArray[i]["ptitle"],allPostArray[i]["pcontent"],allPostArray[i]["pdt"]);
			totalHtml += returnPost(allPostArray[i]["ptitle"],allPostArray[i]["pcontent"],allPostArray[i]["pdt"]);
		}
		
		if(allPostArray[i]["gtype"]=="V"){
			videosHtml += returnVideo(allPostArray[i]["vtitle"],allPostArray[i]["vcont"],allPostArray[i]["vpath"],allPostArray[i]["pdt"]);
			totalHtml += returnVideo(allPostArray[i]["vtitle"],allPostArray[i]["vcont"],allPostArray[i]["vpath"],allPostArray[i]["pdt"]);
		}
		
		if(allPostArray[i]["gtype"]=="D"){
			doubtsHtml += returnDoubt(allPostArray[i]["did"],allPostArray[i]["dquest"],allPostArray[i]["pdt"],allPostArray[i]["replies"]);
			totalHtml += returnDoubt(allPostArray[i]["did"],allPostArray[i]["dquest"],allPostArray[i]["pdt"],allPostArray[i]["replies"]);
		}
	}
	contentHolder.innerHTML = totalHtml;
	//console.log("Finally : ",totalHtml);
	//console.log("All Post length : ",allPostArray.length);
}

function generatePostHtml(){
	console.log("Posts : ",allPostArray);
	console.log("Length : ",allPostArray.length);
}

function loadAll(){
	console.log("all clicked");
	contentHolder.innerHTML = "";
	contentHolder.innerHTML = totalHtml;
}

function loadPost(){
	console.log("post button");
	contentHolder.innerHTML = "";
	contentHolder.innerHTML = postsHtml;
}

function loadDoubts(){
	console.log("doubt button");
	contentHolder.innerHTML = "";
	contentHolder.innerHTML = doubtsHtml;
}

function loadVideos(){
	console.log("video button");
	contentHolder.innerHTML = "";
	contentHolder.innerHTML = videosHtml;
}

function returnVideo(vtitle,vcont,vpath,vdt){
	let htmlSegment = `<div class="media shadow p-3 mb-5 bg-light rounded">
			  <img class="d-flex mr-3 icon" data-src="holder.js/64x64?theme=sky" alt="64x64" src="./images/video_icon.png" data-holder-rendered="true">
			  <div class="media-body">
			    <h5 class="mt-0">${vtitle}</h5>
			    <p>${vcont}</p>
			    <div class="d-flex justify-content-center">
			    	<embed src="./videos/${vpath}" width="480px" height="360px" autoplay="false" />
			    </div>
			    <p>Upladed on : ${vdt}</p>
			  </div>
			</div>`;
	return htmlSegment;
}

function returnPost(ptitle,pcontent,pdt){
	let htmlSegment = `<div class="media shadow p-3 mb-5 bg-light rounded">
			  <img class="d-flex mr-3 icon" data-src="holder.js/64x64?theme=sky" alt="64x64" src="./images/post_icon.png" data-holder-rendered="true">
			  <div class="media-body">
			    <h5 class="mt-0">${ptitle}</h5>
			    <p>${pcontent}</p>
				<p>Uploaded on : ${pdt} </p>
			  </div>
			</div>`;
	return htmlSegment;
}

function returnDoubt(did,dquest,ddt,dreplies){
	//console.log(dreplies);
	let replystat = "";
	if(dreplies!=null){
		for(var j=0;j<dreplies.length;j++){
		
		let subhtml = `
			<div class="commentBox">
				<div id="uname">${dreplies[j]["uname"]}</div>
				<div id="reply">${dreplies[j]["dmsg"]}</div>
				<div id="datime">${dreplies[j]["drdt"]}</div>
			</div>
		`
		
		replystat += subhtml;
		
		}
	}
	
	let htmlSegment = `<div class="media shadow p-3 mb-5 bg-light rounded">
			  <img class="d-flex mr-3 icon" data-src="holder.js/64x64?theme=sky" alt="64x64" src="./images/doubts_icon.png" data-holder-rendered="true">
			  <div class="media-body">
			    <h5 class="mt-0">Doubt Post</h5>
			    <p>${dquest}</p>
				<div id="comments">
					${replystat}
				</div>
					<div id="replyBox">
						${replyBox(did)}
					</div>
				<p>Uploaded on : ${ddt} </p>
			  </div>
			</div>`;
	return htmlSegment;
}

function replyBox(did){
	let replyHtml = '';
	if(utype==1){
		replyHtml = `
		<form action="./formInput?rnum=1&&cid=${cid}&&uid=${uid}" class="form-inline" method="post">
			<input type="hidden" name="did" value="${did}">
			<input type="hidden" name="cname" value="${cname}">
			<input name="dreply" type="text" class="form-control mr-sm-2" style="width:80%;" placeholder="Reply">
			<button type="submit" class="btn btn-primary">Reply</button>
		</form>
	`;
	}
	
	return replyHtml;
}

/*

	<!-- Doubt HTML -->
			<div class="media shadow p-3 mb-5 bg-light rounded">
			  <img class="d-flex mr-3 icon" data-src="holder.js/64x64?theme=sky" alt="64x64" src="./images/doubts_icon.png" data-holder-rendered="true"">
			  <div class="media-body">
			    <h5 class="mt-0">Title</h5>
			    <p>Content...</p>
			    <div id="comments">
			    	<div class="commentBox">
			    		<div id="uname">UNAME</div>
			    		<div id="reply">Comment</div>
			    		<div id="datime">Datetime</div>
			    	</div>
			    	<div class="commentBox">
			    		<div id="uname">UNAME</div>
			    		<div id="reply">Comment</div>
			    		<div id="datime">Datetime</div>
			    	</div>
			    	<div class="commentBox">
			    		<div id="uname">UNAME</div>
			    		<div id="reply">Comment</div>
			    		<div id="datime">Datetime</div>
			    	</div>
			    </div>
			  </div>
			</div>
			<!-- Video HTML -->
			
			<!-- Post HTML -->
			

*/

/*

*/