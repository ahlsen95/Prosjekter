
/*
// TODO:
*/
var myTaskList={
  completed:[],
  incomplete:[]
};
var counter = 1;
const input = document.querySelector("input");

window.onload = function(){
  activeTab();
  addTask("Make a Task!");
}

//Creating a close button and appending it to the items in the list
function addCloseButton(task){
  var span = document.createElement("SPAN");
  var txt = document.createTextNode("\u00D7");
  span.className = "close";
  span.appendChild(txt);
  removeTask(span, task);
  task.appendChild(span);
}

//Removing task when clicking on "x"
function removeTask(span, task){
  span.onclick = function(){
    if(task.classList.value === "checked"){
      var index = myTaskList.completed.indexOf(task);
      myTaskList.completed.splice(index, 1);
      updateTaskList();
    }
    if(task.classList.value === ""){
      var index = myTaskList.incomplete.indexOf(task);
      myTaskList.incomplete.splice(index, 1);
      updateTaskList();
    }
  }
}
// Adding "checked" when clicking on a task
function addChecked(task){
  task.addEventListener("click", function(ev){
    if (ev.target.tagName ==="LI"){
      ev.target.classList.toggle("checked");
      moveTask(ev.target);
    }
  },false);
}

//Moving task between "completed" and "incomplete" based on it being checked or not
function moveTask(task){
  if(task.classList.value === "checked"){
    myTaskList.completed.push(task);
    var index = myTaskList.incomplete.indexOf(task);
    myTaskList.incomplete.splice(index, 1);
    updateTaskList();
  }
  if(task.classList.value === ""){
    myTaskList.incomplete.push(task);
    var index = myTaskList.completed.indexOf(task);
    myTaskList.completed.splice(index, 1);
    updateTaskList();
  }
}

//Adding a task based on input from user
function addTask(task){
  var txt = document.createTextNode(task);
  var li = document.createElement("LI");
  li.appendChild(txt);
  li.id=counter;
  counter++;
  myTaskList.incomplete.push(li);
  addCloseButton(li);
  addChecked(li);
  updateTaskList();
}

//Setting up a new task element
input.addEventListener("keydown", function(event){
  if(event.key === "Enter"){
    var task = document.getElementById("inputTask").value;
    if(validTask(task)){
      addTask(task);
      document.getElementById("inputTask").value = "";
    }else{
      window.alert("You have not written anything! Please fill out a task.")
    }
  }
});

//Checking if task is in a valid form
function validTask(task){
  if (task === ""){
    return false;
  }
  return true;
}

//Sets which tab to be active
function activeTab(){
  var tabs = document.getElementsByClassName("menuTab");

  //Looping through the elements and adding "active" to the clicked tab
  for(var i = 0; i < tabs.length; i++){
    tabs[i].addEventListener("click", function(){
      var current = document.getElementsByClassName("active");
      current[0].className = current[0].className.replace(" active", "");
      this.className += " active";
      updateTaskList();
    });
  }
}

function updateTaskList(){
  var aTab = document.getElementsByClassName("active");
  var list = document.querySelector("#taskList");
  emptyList(list);
  sortTasks();
  if(aTab[0].innerText == "Incomplete"){
    for(var i = 0; i < myTaskList.incomplete.length; i++){
      list.appendChild(myTaskList.incomplete[i]);
    }
  }
  if(aTab[0].innerText == "Completed"){
    for(var i = 0; i < myTaskList.completed.length; i++){
      list.appendChild(myTaskList.completed[i]);
    }
  }
  for(var x in myTaskList){
    if(aTab[0].innerText == "All"){
      for(var i = 0; i < myTaskList[x].length; i++){
        list.appendChild(myTaskList[x][i]);
      }
    }
  }
}

function sortTasks(){
  myTaskList.completed.sort(function(a, b) {
    return (a.id - b.id);
  });
  myTaskList.incomplete.sort(function(a, b) {
    return (a.id - b.id);
  });
}
function emptyList(list){
  while (list.firstChild) {
    list.removeChild(list.firstChild);
  }
}
