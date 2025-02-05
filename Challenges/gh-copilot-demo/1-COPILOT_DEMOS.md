# Get ready
This first challenges needs you to clone the following GitHub Repository: Github Copilot Demo

This repository is a code starter that will help you experiment all capabilities with GitHub Copilot. Take the time to look at the architecture design displayed on the page and when you're ready, clone the repository from the command line and open it in VS Code.
7
```
git clone https://github.com/ccoe-microsoft/ghc-openhack
cd ghc-openhack
code .
```

# Start playing with GitHub Copilot
Once you start typing a prompt and copilot generate proposals, you can use the following shortcuts to interact with Copilot:

- `tab` to accept the current suggestion entirely (most common)
- `ctrl + right arrow` to accept word by word the suggestion (for partial use)
- `alt + ^` to move to next suggestion
- `shift + tab` to go back to the previous suggestion
- `ctrl+enter` to display the copilot pane

If you can't remember it, just hover your pointer on top of a suggestion to make them appear.
<br>

# Challenge

## Natural Language Translations

**Automate text translation**

- Open file `album-viewer/lang/translations.json`
```json
[
    {
        "language": "en",
        "values": {
            "main-title": "Welcome to the world of the future",
            "main-subtitle": "The future is now with copilot",
            "main-button": "Get started"
        }
    }
]
```

- Start adding a new block by adding a "," after the last "}" and press enter

<br>

## Code Generation

**Generate code from prompt**

- Create a new `album-viewer/utils/validators.ts` file and start with the prompt:
```ts
// validate date from text input in french format and convert it to a date object
```

- Copilot can help you also to write `RegExp patterns`. Try these:
```ts
// function that validates the format of a GUID string

// function that validates the format of a IPV6 address string
```

<br>

**Discover new tool and library on the job with Copilot**

- Still on the same `album-viewer/utils/validators.ts` file add the following prompt:
```ts
// validate phone number from text input and extract the country code
```
>For this one it will probably give you proposal that call some methods not defined here and needed to be defined. It's a good opportunity to explore the alternatives using the `ctrl+enter` shortcut to display the copilot pane. 
<br>You can choose one that uses something that looks like coming for an external library and use copilot to import it showing that the tool helps you discover new things.


**Complex algoritms generation**

- In the `albums-api-java/src/main/java/com/ghcopilot/album/controllers/AlbumController.java` file try to complete the `getAlbumById` method by replace the current return:

```java
    // GET: /albums/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Album> getAlbumById(@PathVariable int id) {

    }
```

- In the same file you can show other prompts like:
```java
// function that search album by name, artist or genre

// function that sort albums by name, artist or genre
```

## Big Prompts and Short Prompts

Copilot will probably will always more effective with prompt to generate small but precisely described pieces of code rather than a whole class with a unique multiple lines prompt.

The best strategy i experimented to generate big piece of code, is starting by the basic shell of your code with a simple prompt and then adding small pieces one by one.

**Big prompts that works**

- Back in the `albums-viewer/utils` add a new file `viz.ts` to create a function that generates a graphe. Here is a sample of prompt to do that:

```ts
// generate a plot with d3js of the selling price of the album by year
// x-axis are the month series and y-axis show the numbers of album selled
```
>You can try to add details when typing by adding it or following copilot's suggestions and see what happens

- Once you achieved to generate the code for the chart you probably see that your IDE warn you about the d3 object that is unknow. For that also Copilot helps.
Return on top of the file and start typing `import d3` to let copilot autocomplete

```ts
import d3 from "d3";
```


<!-- Stopped working reccently don't know why
- Another prompt that is totally decorrelated with the codebase but interesting as the prompt can be very long if you follow all suggestion by copilot is the following one:
```ts
// I need a function that finds the way out of a labyrinth. The labyrinth is a matrix of 0 and 1 and 1 represent a wall
```
*I used to show this one in another language like C for example. As it's a common algorithm problem, you can let copilot complete this one for 4 to 5 line to show how precise can be the tools and when you reach the limit to generate something usable* -->

## Code Documentation 

Copilot can understand a natural language prompt and generate code and because it's just language to it, it can also `understand code and explain it in natural language` to help you document your code.

### simple documentation comment

To see that just put you pointer on top of a Class, a method or any line of code and start typing the comment handler for the selected language to trigger copilot. In language like Java, C# or TS for example, just type `// `and let the magic happen.

Here is an example in the `albums-viewer/routes/index.js` file. Insert a line and start typing on line 13 inside the `try block`

```js
router.get("/", async function (req, res, next) {
  try {
    // Invoke the album-api via Dapr
    const url = `http://127.0.0.1:8080/albums`;

```

Continue to play with it and see what happens on other pieces of code.

### standardized documentation comment (JavaDoc, JsDoc, etc...)

For this one, to trigger the documentation comment generation, you need to respect the specific comment format:
-  `/**` (for JS/TS and Java) in the `index.js` file for example


## Code Translation

*Copilot can understand and generate natural languages and code language in both way so by combining everything you can use it to `translate code pieces from a language to another one`*

To translate a piece of code in a specific language, create a new file with the extension of the language you want to translate to (ie: `validator.c` in C language) and try to build the prompt like that:

```c
// translate the following code from typescript to java:
// <code here>
```

And go to a new line to trigger copilot. It will try generate the code in the new language.

```c
// translate the following code from typescript to c:
// function validateDate(date: string): Date {
//     const dateParts = date.split('/');
//     const day = parseInt(dateParts[0], 10);
//     const month = parseInt(dateParts[1], 10) - 1;
//     const year = parseInt(dateParts[2], 10);
//     return new Date(year, month, day);
// }

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct Date {
    int day;
    int month;
    int year;
};

struct Date validateDate(char *date) {
    struct Date d;
    char *dateParts[3];
    char *token;
    int i = 0;

    token = strtok(date, "/");
    while (token != NULL) {
        dateParts[i++] = token;
        token = strtok(NULL, "/");
    }

    d.day = atoi(dateParts[0]);
    d.month = atoi(dateParts[1]) - 1;
    d.year = atoi(dateParts[2]);

    return d;
}
```

*It's not working perfectly every time depending on the size and complexity of the code and the language in input the the one in output but i made a lot of test and it's pretty impressive with common languages: `C, Java, C#, TS, Python, Go, VB.Net, F#, Kotlin, etc...`*

*`It's also able to work on more exotic language`: it worked on an language used by a customer that we didn't even found traces on internet. By the way it's not listed in the languages supported by Copilot but it worked. You can't rely only on it to migrate the whole source code without having the developper to understand what happens but it sure will help accelerate the process.*

## Tests

Copilot can help generate all kind of tests that are written with code. It Includes `unit tests, integration tests, end to end tests, and load testing` tests with jmeters scripts for example.

- Add a new file `validators.test.ts` in the `albums-viewer/tests` folder

- To have good test suggestion, you hould provide some basic informations to Copilot such as the test framework you want to use:

```ts
import { describe }
```

When you start typing the `describe` function, copilot will see you're in test file in TS and suggest you to import the `describe` and `it` functions from Mochai which is a famous test framework for JS/TS.
Accept the suggestion and it will automatically suggest also the `expect` function from Chai: accept it also.

```ts
import {describe, it} from 'mocha';
import {expect} from 'chai';
```

You have your test framework in place! Now just import the functions you want to test by starting a new line by `import` keyword copilot will see you are in a test file, to test some `validators` because of the name and it will suggest something like that:

```ts
import {validateAlbumId} from '../src/validators';
```

It looks ok but because Copilot doesn't have access to all your code, only the open tab and limited informations, you can see that both the path and the function name are wrong.
<br>
... At least he tried ... 
<br>
but it's a good way to show that Copilot is not magic and you have to use it with your other IDE feature and your brain :)

- Accept the suggestion and change the path. You will be able to have VS Code to give you the available function with the `ctrl+space` shortcut.

- Add a comment with the first function you want to test and let the magic happen:


```ts
import {describe, it} from 'mocha';
import {expect} from 'chai';

import {validateDate, validateIPV6} from '../utils/validators';

// test the validataDate function
```
Boom!
```ts	
describe('validateDate', () => {
    it('should return a date object when given a valid date string', () => {
        const date = '01/01/2019';
        const expectedDate = new Date(2019, 0, 1);
        expect(validateDate(date)).to.deep.equal(expectedDate);
    });

    it('should throw an error when given an invalid date string', () => {
        const date = '01/01/2019';
        expect(() => validateDate(date)).to.throw();
    });
});
```

*You can add other `it` block to add more test cases and also add the tests for the other functions. For example try add a new `it` block for the validateDate function to test that it throws and error when given en empty string.*
