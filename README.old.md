### run Sonarqube docker container
```shell
docker run --name sonarqube-custom -p 9000:9000 sonarqube:10.6-community
```

### create the docker container for the gitlab runner
```shell
docker run -d --name gitlab-runner --restart always \
-v /var/run/docker.sock:/var/run/docker.sock \
-v gitlab-runner-config:/etc/gitlab-runner \
gitlab/gitlab-runner:latest
```
### create runner volume
will contain the config.toml file with runner config
```shell
docker volume create gitlab-runner-config
```
### register the runner
```shell
docker run --rm -it -v gitlab-runner-config:/etc/gitlab-runner gitlab/gitlab-runner register
```
params:
- GitLab instance URL: ```https://git.sunto.reply.it```\
- registration token: got from gitlab runner creation\
- executor: ```docker```\

### start mongodb service
```shell
mongod -f ./mongo/mongod.conf
```

### settings on Gitlab

add variables under Project --> CI/CD Settings --> Variables
- SONAR_HOST_URL: http://host.docker.internal:9000
- SONAR_TOKEN: <sonarToken>

### (optional) to manually execute the scan
```shell
mvn clean verify sonar:sonar \
-Dsonar.projectKey=<projectKey> \
-Dsonar.projectName='springboot-demo' \
-Dsonar.host.url=http://localhost:9000 \
-Dsonar.token=<sonarToken>
```
------
- `<projectKey>` can be obtained from http://localhost:9000/admin/projects_management once Sonarqube is running
- `<sonarToken>` can be generated from http://localhost:9000/account/security once Sonarqube is running



SonarQube Integration with GitLab CI

This guide provides steps to integrate SonarQube with your GitLab repository to perform code scans automatically on every code push.

Step 1: Run SonarQube in Docker

To start a SonarQube instance locally using Docker, run the following command:

bash
Copy code
docker run --name sonarqube-custom -p 9000:9000 sonarqube:10.6-community
Once started, SonarQube will be accessible on http://localhost:9000.

Step 2: Set Up GitLab Runner in Docker

2.1 Create GitLab Runner Volume
Create a Docker volume that will store the GitLab Runner configuration file (config.toml):

bash
Copy code
docker volume create gitlab-runner-config
2.2 Start GitLab Runner Container
To start the GitLab Runner container, use the following command:

bash
Copy code
docker run -d --name gitlab-runner --restart always \
-v /var/run/docker.sock:/var/run/docker.sock \
-v gitlab-runner-config:/etc/gitlab-runner \
gitlab/gitlab-runner:latest
2.3 Register the GitLab Runner
Register the GitLab Runner with your GitLab instance:

bash
Copy code
docker run --rm -it -v gitlab-runner-config:/etc/gitlab-runner gitlab/gitlab-runner register
When prompted, provide the following details:

GitLab instance URL: https://git.sunto.reply.it
Registration token: (Get this from your GitLab project under Settings > CI/CD > Runners)
Executor: docker
Step 3: Start MongoDB Service (if needed)

To start a MongoDB service locally (if required for your project), run the following command:

bash
Copy code
mongod -f ./mongo/mongod.conf
Step 4: Configure GitLab for SonarQube Integration

Go to your GitLab project and configure the following CI/CD variables:

SONAR_HOST_URL: http://host.docker.internal:9000
SONAR_TOKEN: <sonarToken> (Generate this from your SonarQube account)
To add these variables, navigate to Project > Settings > CI/CD > Variables in GitLab.

(Optional) Step 5: Manually Trigger a SonarQube Scan

To manually run a SonarQube scan using Maven, execute the following command:

bash
Copy code
mvn clean verify sonar:sonar \
-Dsonar.projectKey=<projectKey> \
-Dsonar.projectName='springboot-demo' \
-Dsonar.host.url=http://localhost:9000 \
-Dsonar.token=<sonarToken>
Additional Information:
The project key can be obtained from http://localhost:9000/admin/projects_management once SonarQube is running.
The SonarQube token can be generated from http://localhost:9000/account/security in your SonarQube account settings.
