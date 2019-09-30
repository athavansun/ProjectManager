INSERT INTO `project_manager`.`users` (`user_id`, `employee_id`, `first_name`, `last_name`) VALUES ('1', '111', 'Athavan', 'sun');
INSERT INTO `project_manager`.`users` (`user_id`, `employee_id`, `first_name`, `last_name`) VALUES ('2', '222', 'Raj', 'k');
INSERT INTO `project_manager`.`users` (`user_id`, `employee_id`, `first_name`, `last_name`) VALUES ('3', '333', 'selvam', 'a');
INSERT INTO `project_manager`.`users` (`user_id`, `employee_id`, `first_name`, `last_name`) VALUES ('4', '444', 'kumar', 's');
INSERT INTO `project_manager`.`users` (`user_id`, `employee_id`, `first_name`, `last_name`) VALUES ('5', '555', 'viki', 'd');

INSERT INTO `project_manager`.`project` (`project_id`, `end_date`, `priority`, `project`, `start_date`, `user_id`) VALUES ('1', '2020-05-13', '30', 'RTFA', '2019-09-12', '1');
INSERT INTO `project_manager`.`project` (`project_id`, `end_date`, `priority`, `project`, `start_date`, `user_id`) VALUES ('2', '2020-10-11', '20', 'LOT', '2019-10-10', '2');
INSERT INTO `project_manager`.`project` (`project_id`, `end_date`, `priority`, `project`, `start_date`, `user_id`) VALUES ('3', '2020-09-25', '10', 'AVITA', '2019-11-24', '4');

INSERT INTO `project_manager`.`parent_task` (`parent_id`, `parent_task`) VALUES ('1', 'implement new server');
INSERT INTO `project_manager`.`parent_task` (`parent_id`, `parent_task`) VALUES ('2', 'server deployment');

INSERT INTO `project_manager`.`task` (`task_id`, `end_date`, `parent_id`, `priority`, `project_id`, `start_date`, `status`, `task`, `user_id`) VALUES
                                        ('1', '2019-09-28', '1', '20', '1', '2019-09-12', 'STARTED', 'create code base', '1');
INSERT INTO `project_manager`.`task` (`task_id`, `end_date`, `parent_id`, `priority`, `project_id`, `start_date`, `status`, `task`, `user_id`) VALUES
                                        ('2', '2019-09-29', '1', '30', '1', '2019-09-10', 'STARTED', 'implement functions', '2');
INSERT INTO `project_manager`.`task` (`task_id`, `end_date`, `parent_id`, `priority`, `project_id`, `start_date`, `status`, `task`, `user_id`) VALUES
                                        ('3', '2019-09-30', '1', '25', '1', '2019-09-21', 'STARTED', 'code validation', '3');
INSERT INTO `project_manager`.`task` (`task_id`, `end_date`, `parent_id`, `priority`, `project_id`, `start_date`, `status`, `task`, `user_id`) VALUES
                                        ('4', '2019-10-22', '2', '10', '2', '2019-10-05', 'STARTED', 'prod deployment', '4');
INSERT INTO `project_manager`.`task` (`task_id`, `end_date`, `priority`, `project_id`, `start_date`, `status`, `task`, `user_id`) VALUES
                                        ('5', '2021-10-10', '10', '3', '2020-10-30', 'STARTED', 'support', '5');
