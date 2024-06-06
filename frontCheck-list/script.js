document.addEventListener('DOMContentLoaded', () => {
    const tasksList = document.getElementById('tasksList');
    const taskNameInput = document.querySelector('input[type="name"]'); 
    const taskDescriptionInput = document.getElementById('TaskDescription');
    const addTaskBtn = document.getElementById('addTaskBtn');

    const apiUrl = 'http://localhost:8080/tasks';

    async function createTask(){
        const taskName = taskNameInput.value;
        const taskDescription = taskDescriptionInput.value;

        const response = await fetch(apiUrl, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({name: taskName, description: taskDescription})
        });

        if(response.ok){
            loadTasks();
        }
    }
    
    const deleteButtons = document.querySelectorAll('.delete-task-btn');
    deleteButtons.forEach(button => {
        button.addEventListener('click', () => {
            const taskId = button.getAttribute('data-id');
            deleteTaskById(taskId);
        });
    });

    addTaskBtn.addEventListener('click', createTask);

    loadTasks();
});

async function loadTasks(filter = ''){
    const apiUrl = 'http://localhost:8080/tasks';
    const response = await fetch(`${apiUrl}${filter}`);
    const tasks = await response.json();

    const tasksList = document.getElementById('tasksList');
    tasksList.innerHTML = '';
    tasks.forEach(task => {
        const taskItem = document.createElement('li');
        taskItem.classList.add('task');
        taskItem.innerHTML= `
            <div class="check-text">
            <div class="custom-checkbox">
                <input type="checkbox" id="${task.id}">
            </div>
            <div class="text-task">    
                <span class="name-task">${task.name}</span>
                <span class="description-task">${task.description}</span>
            </div>  
            </div>
            <button class="delete-task-btn" data-id="${task.id}">
               <span class="icontrash"><i class="fas fa-trash"></i></span>
            </button>
        `;
        tasksList.appendChild(taskItem);
    });

    const deleteButtons = document.querySelectorAll('.delete-task-btn');
    deleteButtons.forEach(button => {
        button.addEventListener('click', () => {
            const taskId = button.getAttribute('data-id');
            deleteTaskById(taskId);
        });
    });
}

async function deleteTaskById(taskId) {
    const apiUrl = 'http://localhost:8080/tasks';
    const response = await fetch(`${apiUrl}/${taskId}`, {
        method: 'DELETE'
    });

    if(response.ok) {
        loadTasks();
    } else {
        console.error('Failed to delete task');
    }
}