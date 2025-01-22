# Use Copilot Chat to Improve Code Quality

GitHub Copilot is a generative AI and thus, perfect to generate code, but it has powerful analysis capabilities on your code that can be used in several cases to improve code quality like: find security issues, bad practices in your code and generate a fix, refactor and add comments to legacy code, generate tests, etc.

If you already feel comfortable with it you can jump to the next section.

## Let's Start

To start using GitHub Copilot Chat, you first need to:

1. Have a valid GitHub Copilot license (personal, business or enterprise).
2. Install the extension in your IDE. For VS Code, you can find it directly by searching for GitHub Copilot Chat in the extensions tab.

## Clone the Repository

We will use the same repository as the previous section to show how to use Copilot Chat to improve code quality. If you already have it, you can skip this step.

You need to clone the following GitHub Repository: GitHub Copilot Demo

This repository is a code starter that will help you experiment with all capabilities of GitHub Copilot. Take the time to look at the architecture design displayed on the page and when you're ready, clone the repository from the command line and open it in VS Code.

```bash
git clone https://github.com/ccoe-microsoft/ghc-openhack
cd ghc-openhack
code .
```

## Start Playing with the Chat

Once Copilot Chat is set up, you can start using it:

- By accessing the **chat view** from the left toolbar of your IDE (chat icon)
- By pressing `Ctrl + Shift + i` shortcut for a quick **inline question** to the chat

The first one is a sticky version, very useful to keep the chat open and ask questions to Copilot. The second one is a quick way to ask a question and get an answer and launch commands.

### Chat View

The chat view gives you a full chat experience, integrated as any other tool view in your IDE. Once the view is open you can start chatting with Copilot as your personal code coach. It keeps the history of the conversation and you can ask questions related to the previous answers. It also provides suggestions for questions along the way. You can:

- Ask general questions about coding in any language or best practices
- Ask to generate or fix code related to the current file and inject the code directly in the file

It’s a more high-level Copilot than the vanilla Copilot which is specialized in providing code completion.

Try it with a few questions like:
```
> How to generate a random number in Java? 
> What is the best way to secure a route in ASP.NET Core? 
> What is the easiest way to generate a static website with NodeJS?
```
Try it then with some of your code files in the repository. Open a file and try asking:
```
> Can you explain what this code does? 
> (with only part of the code selected) Can you explain what the selected code does? 
> Can you generate a function that returns a random number between 1 and 10? 
> Can you add documentation comments to this function?
```
Try also using the question suggestions that appear along the way.

### Inline Question

The inline question is a quick way to ask a question to Copilot and get an answer. It’s a good way to ask a question about a specific piece of code. It’s also a good way to launch commands to Copilot. You can ask it to generate code, fix code, generate tests, etc.

Try it by pressing `Ctrl + Shift + i` and type the same type of commands you tried in the chat view.

### Slash Commands

To further help Copilot give you more relevant answers, you can choose a topic for your questions through “slash commands.”

You can prepend your chat inputs with a specific topic name to help Copilot give you a more relevant response. When you start typing `/`, you’ll see the list of possible topics:

- `/explain`: Explain step-by-step how the selected code works.
- `/fix`: Propose a fix for the bugs in the selected code.
- `/help`: Prints general help about GitHub Copilot.
- `/tests`: Generate unit tests for the selected code.
- `/vscode`: Questions about VS Code commands and settings.
- `/clear`: Clear the session.

## Secure Your Code

Copilot can help you find security issues in your code and fix them. It can also help you find bad practices in your code and fix them. Let’s see how it works.

Open the `album-api/Controllers/UnsecuredController.java` file and type questions like these to the chat:
```
> Can you check this code for security issues? 
> Do you see any quality improvements to do on this code?
```
Once you have the answer, you can ask to fix the issues by typing:
```
> Can you propose a fix?
```
When you have the fix in the code you can choose to **copy it or inject it directly in the file** by hovering over the code block in the chat and selecting the right option on the top left.

## Code Explanation and Documentation

You can use Copilot Chat to explain code to you. It can explain the code in natural language or generate documentation comments for you. Let’s try that with the following commands:
```
> /explain 
> Generate documentation comments for this code
```
## Code Refactoring

More impressively, Copilot Chat can help you refactor your code. It can help you rename variables, extract methods, extract classes, etc.

You can try some of these commands on the `album-api/Controllers/UnsecuredController.java` file:
```
> Extract methods 
> Create Async version of each method when it makes sense
```
## Code Translation

*Copilot can understand and generate natural languages and code languages both ways so by combining everything you can use it to ***translate code pieces from one language to another***.

To translate a piece of code in a specific language, open it and ask the chat to translate it to another language. For example, open the `validators.ts` file created in the first section dedicated to Copilot autocompletion and ask to translate it to C.

In case of dealing with legacy code like COBOL, for example, it can be very useful. Open the `legacy/albums.cbl` file and try translating the code to Python.

## Tests Generation

Copilot can also help you generate tests for your code. It can generate unit tests, integration tests, end-to-end tests, and load testing tests with JMeter scripts, for example.

Open the `album-api/Controllers/UnsecuredController.java` file and type questions like these to the chat:
```
> Generate a unit tests class for this code
```
You can also use Copilot to help you generate Stubs and Mocks for your tests.

```
> Generate a mock for FileStream class 
> Use that mock in the unit tests
```

> **Note:** Remember that Copilot Chat keeps track of the previous Q&A in the conversation, that’s why you can reference the previously generated mock and test easily.

## Use Chat Participants

Chat participants are like specialized experts who can assist you with specific tasks. You can mention them in the chat using the `@` symbol. Currently, there are three Chat participants available for Visual Studio Code:

- `@workspace`: This chat participant has knowledge about the code in your workspace and can help you navigate it by finding relevant files or classes. The `@workspace` chat participant uses a meta prompt to determine what information to collect from the workspace to help answer your question.
- `@vscode`: This chat participant is knowledgeable about commands and features in the VS Code editor itself, and can assist you in using them.
- `@terminal`: This chat participant has context about the Visual Studio Code terminal shell and its contents.

They may not be super rich for the moment but their features will continue to grow over time. Here are some examples:

Open the side Chat panel and type `@workspace /new` to specify that you want to create a new project. For instance, try to create a Java Spring Boot project:
``` 
> @workspace /new create a new java spring boot project, with three controllers: Index, Users, and Products.
```
It should create a structured project and even a new button to create the file. Click on “Create workspace” to see files being created.

## Tips

GitHub Copilot Chat is very handy but for a developer, leaving the keyboard and having to take the mouse to open the Chat tab can be boring. You can directly call the Chat inside the code editor.

1. Open any file containing code.
2. Use the shortcut `Ctrl + i`. It should open the Quick chat popup, a small chat window where you put your cursor.
3. Type any command to generate some code (e.g., “Create a Java class named Toto”). The generated code is injected inside the current file which may be what you want.
