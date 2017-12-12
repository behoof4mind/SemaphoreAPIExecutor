### SemaphoreAPIExecutor
This program can helps you to execute Ansible Semaphore tasks by Semaphore API.

If you need to execute Semaphore tasks you can execute this program with following parameters:
```
usage: semaphorexec [-h] [-i <arg>] [-k] [-p <arg>] [-P <arg>] [-t <arg>]
       [-U <arg>] [-y <arg>]

Options:
 -h,--help                Show this help message
 -i,--ipaddr <arg>        Semaphore ip address
 -k,--skip-ssl-check      Skip SSL/TLS certificate check
 -p,--project-id <arg>    Semaphore project ID
 -P,--password <arg>      Semaphore API user password
 -t,--template-id <arg>   Semaphore template ID
 -U,--username <arg>      Semaphore API username
 -y,--yml-file <arg>      Executable Ansible YML-file
``` 
 Example: -i 192.168.1.1 -p 10 -t 3 -y main.yml -U admin -P password