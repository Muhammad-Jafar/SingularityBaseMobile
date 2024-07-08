# Contributing
Open for contribution.

## Registration
Contact me via [email: stefanus.ayudha@gmail.com](mailto:stefanus.ayudha@gmail.com) with the following format:<br/>
```markdown
Subject: 
I want to contribute to Singularity Mobile APP!

Body:
Github user name: John Doe.
Task I want to take: [link to task].
```

## Project
Brows the task you want to take here:
[Singularity Project](https://github.com/orgs/SingularityIndonesia/projects/1).
You can take any task available that is ready.

## Pattern convention
Designed by [Stefanus Ayudha](https://github.com/stefanusayudha).

The `base` branch serves as the pattern convention for this application. It represents the minimum example of the application and is used as the standard pattern for contributors.

Every contributor is required to adhere to this established pattern and architecture.

## Git flow
![Singularity GitFlow - Gitflow.jpg](Singularity%20GitFlow%20-%20Gitflow.jpg)
See: [Singularity Gitflow - Miro.](https://miro.com/app/board/uXjVMS5Omk8=/?share_link_id=784438148126)

## Note (GitFlow)
1. **Rebase Rule**
   Rebasing should only be done if the changes are foundational to the project. For example, if there are changes to the API in the core module during a sprint that need to be made. Or, for example, if there are additions to source material such as strings, colors, components, tokens, and so on. Changes at the feature level should not be rebased. Changes at the feature level should be merged without rebasing.
   Rebase notation should be able to be defined as the following expressiong: `BaseV1.2.0 = BaseV1.1.x + resources + component + token + ...` and feature must not be included to the base.
2. **Changes in module structure must add major version**
   You cannot restructure a module and update the minor version. You must update a major version and better in seperated project.
3. **Early development**
   In the early stage of the development, we usually make alot of changes to the module structure and core api. In that case, make sure that you are following the rebase rule.

## Commit convention
- `fix($scope): $message.`

  To commit a bug fix.
- `feat($scope): $message.`

  To commit a feature.
- `BREAKING CHANGE($scope): $message.`

  To commit breaking changes (rename, replace, move, delete, etc.) in the context of "not bug fixing".
- `doc($scope): $message.`

  To commit documentation.

## Note (Convention Commit)
1. **Committing changes with side effect**
   If your changes somehow affecting another module that is not associated with your module / scope, and somehow you cannot separate them to separated commits, you must add `!` at the beginning of your commit message and add `ALSO:` to provides the effects.

eg:
```
!fix(LoginScreen): add error handler for MCancelationException.
ALSO:
- ($scope): $changes.
- ($scope): $changes. 
```

Resources category includes: Strings, Icons.

## Commit Procedure
Commit the lowest module first.

1. Set the commit view into module.

   ![Screenshot 2024-03-10 at 19.14.55.png](Screenshot%202024-03-10%20at%2019.14.55.png)
2. Commit modules in order from the lowest to highest.

   ![Screenshot 2024-03-10 at 19.19.43.png](Screenshot%202024-03-10%20at%2019.19.43.png)

3. Commit only what is necessary.

   ![Screenshot 2024-03-10 at 19.21.44.png](Screenshot%202024-03-10%20at%2019.21.44.png)

