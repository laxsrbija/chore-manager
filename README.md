<p align="center">
<img src="https://raw.githubusercontent.com/laxsrbija/chore-manager/main/chore-manager-frontend/src/assets/icon.png" width="150">
</p>

![](https://raw.githubusercontent.com/laxsrbija/chore-manager/main/resources/chore-manager.png)

## About the project

Chore Manager aims to help you keep track of your recurring 
household tasks. The application allows users to create a customized list of chores, 
set reminders for when they need to be completed, and track their progress over time.

The goal of this project is to make it easy to stay on top of your chores, 
so you can spend more time doing the things you love.

## Features

* Track recurring tasks that either need to be done on a dynamic schedule or exact dates
* Organize tasks per items and categories
* Choose how many days in advance to notify users assigned to a specific task
* Notify other assigned users via email that a task has been completed

## Usage

The easiest way to deploy the Chore Manager is via Docker:

```
docker run -d -p 8080:8080 --name chore-manager \
    -v /config/directory:/opt/chore-manager/resources \
    -t laxsrbija/chore-manager:latest
```

The mounted host directory must contain an `application.properties` file used to configure the application.
A sample configuration can be found [here](chore-manager-backend/chore-manager-application/src/main/resources/application-dev.properties).

### Updates

![GitHub Workflow Status](https://img.shields.io/github/actions/workflow/status/laxsrbija/chore-manager/publish.yml?label=deployment&style=for-the-badge)

Chore Manager utilises a rolling release; the `main` branch is considered stable, 
and each new push will publish a new Docker image.

You can always stay up-to-date by using [Watchtower](https://github.com/containrrr/watchtower).

### Development environment

You can start the local environment via Docker Compose by executing `docker compose up` in the project root directory.
This will also start a MailCatcher instance for testing emails and preload demo data.

Use either of the following accounts to log in:

* charles@example.com / demo
* michelle@example.com / demo

## Roadmap

While the core features are complete, the project is pretty much still a work in progress.  
This section lists some upcoming features.

- **Manage all resources via a web UI**  
Even though a web UI already exists, it can only be used to track existing tasks. All other
resources have to be managed by calling REST endpoints directly.
- **Tailor content to the current user**  
The current authentication mechanisms exist mainly to prevent unauthorized access to the service.
Instead, list only the tasks assigned to the currently logged-in user.
- **Role-based authorization**  
Provide access to management REST endpoints only to those users with the appropriate role. Also, allow
these users to manage others.
- **Alternative notification providers**  
The system currently sends notifications exclusively via email. 
Alternative notification providers can also be added (e.g. [ntfy](https://github.com/dschep/ntfy)).

## License

Distributed under the MIT License. See [LICENSE](LICENSE) for more information.  
Icon by [alimasykurm](https://thenounproject.com/alimasykurm/) from Noun Project.
