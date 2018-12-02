
/*
// TODO: Sortere oppgavene etter hvem som er huket av og ikke.
*/
function test(){
  var myTaskList = document.getElementById("taskList").getElementsByTagName("LI");
  for(var i = 0; i < myTaskList.length; i++){
    if(myTaskList[i].classList.value){
      myTaskList[i].style.display ="none";
    }
  }
}

//Creating a close button and appending it to the items in the list
function addCloseButton(){
  var myTaskList = document.getElementById("taskList").getElementsByTagName("LI");
  for(var i = 0; i < myTaskList.length; i++){
    var span = document.createElement("SPAN");
    var txt = document.createTextNode("\u00D7");
    span.className = "close";
    span.appendChild(txt);
    if(myTaskList[i].childNodes.length < 2){
      removeTask(span);
      myTaskList[i].appendChild(span);
    }
  }
}

//Removing task when clicking on "x"
function removeTask(span){
  span.onclick = function(){
    var task = this.parentElement;
    var list = document.getElementById("taskList");
    list.removeChild(task);
  }
}

// Adding "checked" when clicking on a task
function addChecked(){
  var list = document.querySelector("#taskList");
  list.addEventListener("click", function(ev){
    if (ev.target.tagName ==="LI"){
      ev.target.classList.toggle("checked");
    }
  },false);
}

//Adding a task based on input from user
function addTask(task){
  var myTaskList = document.getElementById("taskList");
  var txt = document.createTextNode(task);
  var li = document.createElement("LI");
  li.appendChild(txt);
  myTaskList.appendChild(li);
  addCloseButton();
  addChecked();
}

//Setting up a new task element
function newTask(){
  if(event.key === "Enter"){
    var task = document.getElementById("inputTask").value;
    if(validTask(task)){
      addTask(task);
      document.getElementById("inputTask").value = "";
    }else{
      window.alert("You have not written anything! Please fill out a task.")
    }
  }
}

//Checking if task is in a valid form
function validTask(task){
  if (task === ""){
    return false;
  }
  return true;
}
