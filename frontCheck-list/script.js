document.addEventListener('DOMContentLoaded', () => {
    const tasksList = document.getElementById('tasksList');
    const taskNameInput = document.querySelector('input[type="name"]'); // Adjusted selector
    const taskDescriptionInput = document.getElementById('TaskDescription');
    const addTaskBtn = document.getElementById('addTaskBtn');
    const filterNameBtn = document.querySelector('.dropdown-content a[href="#"]'); // Adjusted selector for filter by name
    const filterDateBtn = document.querySelector('.dropdown-content a[href="#"]'); // Adjusted selector for filter by date

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

    async function loadTasks(filter = ''){
        const response = await fetch(`${apiUrl}${filter}`);
        const tasks = await response.json();

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
                <button class="delete-task-btn" onclick="deleteTaskById(${task.id})">
                   <span class="icontrash"><i class="fas fa-trash"></i></span>
                </button>
            `;
            tasksList.appendChild(taskItem);
        });
    }

    function deleteTaskById(taskId) {
        // Send a DELETE request to the server to delete the task with the given taskId
        fetch(`http://localhost:8080/tasks/${taskId}`, {
            method: 'DELETE'
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to delete task');
            }
            // If deletion is successful, reload the tasks
            loadTasks();
        })
        .catch(error => {
            console.error('Error deleting task:', error);
        });
    }
    
    document.addEventListener('click', function(event) {
        if (event.target && event.target.classList.contains('delete-task-btn')) {
            const taskId = event.target.dataset.taskId;
            deleteTaskById(taskId); 
        }
    });
    
    
    

    function filterTasksByName() {
        loadTasks('?sort=name');
    }

    function filterTasksByDate() {
        loadTasks('?sort=date');
    }
        
    addTaskBtn.addEventListener('click', createTask);
    filterNameBtn.addEventListener('click', filterTasksByName);
    filterDateBtn.addEventListener('click', filterTasksByDate);

    loadTasks(); // Initial load
});
