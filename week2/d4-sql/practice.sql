/*
1. ER Diagram for Twitter

User
- userId (PK)
- username
- email
- password
- tweetList[] (FK, 1-M)
- favoritedTweets[] (FK, 1-M)
- following[] (FK, M-M)

Tweet
- tweetId (PK)
- text
- date

2. Employee queries
a: find # employees in each department
    select COUNT(Employee.id)
    from Employee
    groupBy Employee.dept_id

    select dept_id, COUNT(*) as num_employees
    from Employee
    groupBy dept_id

b: get highest salary per dept group
    select salary
    from Employee
    groupBy Employee.dept_id
    having MAX(salary)

    select dept_id, MAX(salary) as highest_salary
    from Employee
    groupBy dept_id

c: find employees with top salary per department
    select *
    from Employee
    groupBy Employee.dept_id
    having MAX(salary)

    select e.*
    from Employee e
    join (
        select dept_id, MAX(salary) as highest_salary
        from Employee
        groupBy dept_id
    ) t on e.dept_id == t.dept_id and e.salary == t.highest_salary

3. customer and order table queries
select customer_id, customer_name
from Order o
join Customer c on o.customer_fk == c.customer_id
groupBy customer_id
having COUNT(o.order_id) > 3
where o.date < 30 days
where o.date >= DATE_SUB(CURDATE(), INTERVAL 30 DAY)

4. employee and salary queries
select *
from Employee e
join Salary s on e.employee_id == s.employee_id
groupBy employee_id
having LATEST(effective_date)

1: get the latest salary and date per employee
(select id, MAX(effective_date) as latestDate, salary
from salary s
groupBy id) as lastSalaries

2: join with employee table and filter
select *
from employee e
join lastSalaries ls on e.id == ls.id
*/