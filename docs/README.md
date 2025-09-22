# **PrintBot** 🤖 - User Guide

[![Java](https://img.shields.io/badge/Java-11+-orange.svg)](https://www.oracle.com/java/)
[![JavaFX](https://img.shields.io/badge/JavaFX-11+-blue.svg)](https://openjfx.io/)

> "The secret of getting ahead is getting started. The secret of getting started is breaking your complex, overwhelming tasks into small, manageable tasks, and then starting on the first one."
>
> — Mark Twain [(source)](https://www.goodreads.com/quotes/219455-the-secret-of-getting-ahead-is-getting-started-the-secret)

## User Guide

- [Installation](#-Installation)
- [Commands](#-Commands)
- [Other Features](#-Other-Features)
- [PrintBot's UI (v0.1)](#-PrintBot's-UI)
- [PrintBot's GUI (v0.2 and later)](#-PrintBot's-GUI)
- [Technical Architecture](#-Technical-Architecture)

## Installation

1.  Download it from [here](https://github.com/Print2PDF/ip/releases).
2.  Double-click the latest PrintBot.jar file.
3.  Add your tasks using simple commands.
4.  Let it manage your tasks for you 😊

## Commands

PrintBot allows you to use text-based commands to:
- [Add different types of tasks](#-Adding-tasks)
- [Delete existing tasks](#-Deleting-tasks)
- [Mark and unmark tasks as completed](#-Managing-tasks)
- [View all tasks,](#-Viewing-tasks)
- or search for a specific tasks

### Adding tasks

* [x] Add simple tasks with `todo`
* [x] Add time sensitive tasks with `deadline`
* [x] Schedule events with `event`

| Type | Format | Example |
|:---|:---|:---|
| Todo | `todo <description>` | `todo read book` |
| Deadline | `deadline <description> /by <due date> ` | `deadline return book /by 11/09/2025 17:00` |
| Event | `event <description> /from <start> /to <end>` | `event meeting /from 24/08/2025 14:00 /to 24/08/2025 16:00` |

`datetime` is in `dd/MM/yyyy HH:mm` format

### Deleting tasks

* [x] Delete tasks with `delete`

| Type | Format | Example |
|:---|:---|:---|
| Any Task | `delete <index>` | `delete 2` |

`delete` uses ***1-based indexing***
`<index>` must be a valid task number

## Managing tasks

* [x] Mark tasks as done with `mark` 👍

| Type | Format | Example |
|:---|:---|:---|
| Any Task | `mark <index>` | `mark 1` |

* [x] Mark tasks as not done with `unmark` 👎

| Type | Format | Example |
|:---|:---|:---|
| Any Task | `unmark <index>` | `unmark 1` |

`mark` and `unmark` use ***1-based indexing***

### Viewing tasks

See all tasks with:

* [x] View all tasks with `list`

| Type | Format | Example |
|:---|:---|:---|
| Any Task | `list` | `list` |

Looking for a specific task? Use:

* [x] Search for task with `find`

| Type | Format | Example |
|:---|:---|:---|
| Any Task | `find <word>` | `find book` |

## Other Features

### What else can PrintBot do?

* [x] Automatically save and load your previous tasks

### Still feeling lost?

* [x] See all available commands with `help` 😊

## PrintBot's UI

PrintBot v0.1 uses text-based ui to interact with the user.

```
____________________________________________________________
Hello! I'm printbot.
What can I do for you?
____________________________________________________________

todo read book
____________________________________________________________
 Got it. I've added this task:
   [T][ ] read book
 Now you have 1 tasks in the list.
____________________________________________________________

bye
____________________________________________________________

Bye. Hope to see you again soon!
____________________________________________________________

```

## PrintBot's GUI

PrintBot v0.2 and later uses JavaFX for a better, interactive application

<img width="503" height="785" alt="Screenshot 2025-09-19 120147" src="https://github.com/user-attachments/assets/cea65673-94da-4123-b805-eae7824d8775" />
<img width="953" height="787" alt="Ui" src="https://github.com/user-attachments/assets/e12a5c0b-8fb6-467a-9203-f5b1a3b7ff43" />

## Technical Architecture

### Language
PrintBot uses Java:
```Java
public class Launcher {

    public static void main(String[] args) {
        Application.launch(printbot.gui.Main.class, args);
    }

}
```

