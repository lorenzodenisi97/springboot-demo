# SonarQube Integration with GitLab CI

This guide provides steps to integrate SonarQube with your GitLab repository to perform code scans automatically on every code push.

## Step 1: Run SonarQube in Docker

To start a SonarQube instance locally using Docker, run the following command:

```bash
docker run --name sonarqube-custom -p 9000:9000 sonarqube:10.7-community
```

Once started, SonarQube will be accessible on [http://localhost:9000](http://localhost:9000).

## Step 2: Set Up GitLab Runner in Docker

### 2.1 Create GitLab Runner Volume
Create a Docker volume that will store the GitLab Runner configuration file (`config.toml`):

```bash
docker volume create gitlab-runner-config
```

### 2.2 Start GitLab Runner Container
To start the GitLab Runner container, use the following command:

```bash
docker run -d --name gitlab-runner --restart always \
-v /var/run/docker.sock:/var/run/docker.sock \
-v gitlab-runner-config:/etc/gitlab-runner \
gitlab/gitlab-runner:latest
```

### 2.3 Register the GitLab Runner
Register the GitLab Runner with your GitLab instance:

```bash
docker run --rm -it -v gitlab-runner-config:/etc/gitlab-runner gitlab/gitlab-runner register
```

When prompted, provide the following details:

- **GitLab instance URL**: `https://git.sunto.reply.it`
- **Registration token**: (Get this from your GitLab project under **Settings** > **CI/CD** > **Runners**)
- **Executor**: `docker`

## Step 3: Start MongoDB Service (if needed)

To start a MongoDB service locally (if required for your project), run the following command:

```bash
mongod -f ./mongo/mongod.conf
```

## Step 4: Configure GitLab for SonarQube Integration

Go to your GitLab project and configure the following CI/CD variables:

1. **SONAR_HOST_URL**: `http://host.docker.internal:9000`
2. **SONAR_TOKEN**: `<sonarToken>` (Generate this from your SonarQube account)

To add these variables, navigate to **Project** > **Settings** > **CI/CD** > **Variables** in GitLab.

## (Optional) Step 5: Manually Trigger a SonarQube Scan

To manually run a SonarQube scan using Maven, execute the following command:

```bash
./gradlew clean build sonarqube \
-Dsonar.projectKey=<project_key> \
-Dsonar.projectName='springboot-demo' \
-Dsonar.host.url=http://localhost:9000 \
-Dsonar.token=<sonarqube_token>
```

### Additional Information:
- The **project key** can be obtained from [http://localhost:9000/admin/projects_management](http://localhost:9000/admin/projects_management) once SonarQube is running.
- The **SonarQube token** can be generated from [http://localhost:9000/account/security](http://localhost:9000/account/security) in your SonarQube account settings.