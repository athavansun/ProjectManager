export const actionTypes = {
  project: {
    onProjectEditClick: "ON_EDIT_CLICK_PROJECT",
    loadProjectList: "LOAD_PROJECT_LIST",
    resetProject: "RESET_PROJECT",
    onProjectChange: "ON_PROJECT_CHANGE",
    addProject: "ADD_PROJECT",
    onProjectDeleteClick: "ON_DELETE_CLICK_PROJECT",
    removeProject: "REMOVE_PROJECT",
    editProject: "EDIT_PROJECT"
  },
  task: {
    onTaskChange: "ON_TASK_CHANGE",
    addTask: "ADD_TASK",
    loadParentTaskList: "LOAD_PARENT_TASK_LIST",
    resetTask: "RESET_TASK",
    onTaskEditClick: "ON_EDIT_CLICK_TASK",
    onTaskDeleteClick: "ON_DELETE_CLICK_TASK",
    onSetParentTask: "ON_SET_PARENT_TASK",
    removeTask: "REMOVE_TASK",
    editTask: "EDIT_TASK",
    loadTaskList: "LOAD_TASK_LIST",
    searchTasksByProject: "SEARCH_TASK_BY_PROJECT",
    onTabChange: "ON_TAB_CHANGE"
  },
  user: {
    loadUserList: "LOAD_USER_LIST",
    resetUser: "RESET_USER",
    onEditClick: "ON_EDIT_CLICK",
    removeUser: "REMOVE_USER",
    editUser: "EDIT_USER",
    onUserChange: "ON_USER_CHANGE",
    addUser: "ADD_USER",
    onDeleteClick: "ON_DELETE_CLICK"
  },
};
