
# 🧭 Mermaid Diagram Guide

Mermaid diagrams let you create flowcharts, architecture maps, sequence diagrams, and more — using only **plain text** inside Markdown. They are lightweight, fast to write, and perfect for documentation (GitHub, Notion, Docusaurus, etc.).

---

## 1. Basic Structure

A Mermaid diagram starts with **triple backticks** (```) and the language tag `mermaid`:

```mermaid
graph TD
    A[Start] --> B[Do something]
    B --> C{Is it done?}
    C -->|Yes| D[Finish]
    C -->|No| B
```

---

## 2. Graph Directions

After `graph`, specify the flow direction:

| Direction | Meaning |
|------------|----------|
| `TD` | Top → Down (default) |
| `BT` | Bottom → Top |
| `LR` | Left → Right |
| `RL` | Right → Left |

```mermaid
graph LR
    A --> B --> C
```

---

## 3. Node Shapes

| Shape | Syntax | Example |
|--------|---------|----------|
| Rectangle | `A[Text]` | `A[Task]` |
| Rounded rectangle | `A(Text)` | `A(Start)` |
| Circle | `A((Circle))` | `A((Node))` |
| Diamond (Decision) | `A{Yes or No?}` | `A{Check}` |
| Asymmetrical | `A>This is weird<` | `A>Alt<` |

```mermaid
graph TD
    A(Start) --> B[Process]
    B --> C{Condition?}
    C -->|Yes| D[Do thing]
    C -->|No| E[Stop]
```

---

## 4. Arrows and Links

| Arrow | Meaning |
|--------|----------|
| `-->` | Standard arrow |
| `---` | Line without arrow |
| `-.->` | Dotted arrow |
| `==>` | Thick arrow |
| `-->|"Text"|` | Arrow with label |

```mermaid
graph LR
    A -->|OK| B
    A -.->|Retry| C
    B ==> D
```

---

## 5. Subgraphs (Grouping)

```mermaid
graph TD
    subgraph Backend
        DB[(Database)] --> API[REST API]
    end
    subgraph Frontend
        UI[Web UI]
    end
    UI --> API
```

---

## 6. Common Diagram Types

### 🪜 Sequence Diagram
```mermaid
sequenceDiagram
    Alice->>Bob: Hello Bob
    Bob-->>Alice: Hi Alice
    Alice-)Bob: Bye!
```

### 🏗️ Class Diagram
```mermaid
classDiagram
    class Animal {
        +String name
        +eat()
        +sleep()
    }
    class Dog {
        +bark()
    }
    Animal <|-- Dog
```

### 🧱 State Diagram
```mermaid
stateDiagram-v2
    [*] --> Idle
    Idle --> Running : start
    Running --> Idle : stop
```

### 🧠 ER Diagram
```mermaid
erDiagram
    USER ||--o{ ORDER : places
    ORDER ||--|{ LINE_ITEM : contains
    PRODUCT ||--o{ LINE_ITEM : includes
```

---

## 7. Tips

✅ Use descriptive node IDs  
✅ Indent for hierarchy  
✅ Use subgraphs for grouping

---

## 8. Example: Clean Architecture

```mermaid
graph TD

    subgraph App [APP: Entry Points]
        A1[app-android]
        A2[app-ios]
        A3[app-desktop]
        A4[app-web]
    end

    subgraph Feature [FEATURE: Business features]
        F1[feature-study]
        F2[feature-dictionary]
    end

    subgraph Shared [SHARED: Cross-feature utilities]
        S1[infrastructure]
        S2[database]
    end

    subgraph Core [CORE: Business logic]
        C1[domain]
        C2[application]
    end

    A1 --> F1
    A1 --> F2
    F1 --> S1
    F2 --> S1
    S1 --> C1
    C1 --> C2
```

---

## 9. Rendering Mermaid

You can preview Mermaid diagrams in:
- **GitHub Markdown**
- **VS Code** with “Markdown Preview Mermaid Support”
- **Obsidian**, **Notion**, **Docusaurus**, **MkDocs**
- [Mermaid Live Editor](https://mermaid.live)

# Mermaid Diagrams Guide

## What is Mermaid?

Mermaid is a text-based diagramming tool that creates diagrams from markdown-like syntax. It's supported in GitHub, GitLab, Notion, and many documentation platforms.

## Basic Syntax

Wrap your Mermaid code in a fenced code block with `mermaid` as the language:

```
```mermaid
graph TD
    A[Start] --> B[End]
```
```

## 1. Flowcharts

### Basic Example
```
```mermaid
graph TD
    A[Start] --> B{Decision?}
    B -->|Yes| C[Action 1]
    B -->|No| D[Action 2]
    C --> E[End]
    D --> E
```
```

### Direction Options
- `graph TD` or `graph TB` - Top to bottom
- `graph LR` - Left to right
- `graph RL` - Right to left
- `graph BT` - Bottom to top

### Node Shapes
- `A[Rectangle]` - Rectangle
- `B(Rounded)` - Rounded edges
- `C([Stadium])` - Stadium shape
- `D[[Subroutine]]` - Subroutine
- `E[(Database)]` - Database
- `F((Circle))` - Circle
- `G{Diamond}` - Diamond (decision)
- `H{{Hexagon}}` - Hexagon

### Arrow Types
- `-->` - Arrow
- `---` - Plain line
- `-.->` - Dotted arrow
- `==>` - Thick arrow
- `-->|Text|` - Arrow with label

## 2. Sequence Diagrams

Show interactions between objects over time.

```
```mermaid
sequenceDiagram
    participant User
    participant Browser
    participant Server
    
    User->>Browser: Enter URL
    Browser->>Server: HTTP Request
    Server-->>Browser: HTTP Response
    Browser-->>User: Display Page
```
```

### Advanced Features
- `->>` - Solid arrow
- `-->>` - Dotted arrow
- `--)` - Async arrow
- `+` and `-` for activation/deactivation
- `Note right/left of Actor: Text` for notes
- `loop`, `alt`, `opt` for control flow

## 3. Class Diagrams

Show object-oriented class relationships.

```
```mermaid
classDiagram
    Animal <|-- Dog
    Animal <|-- Cat
    Animal : +String name
    Animal : +int age
    Animal : +makeSound()
    
    class Dog{
        +String breed
        +bark()
    }
    
    class Cat{
        +String color
        +meow()
    }
```
```

### Relationship Types
- `<|--` - Inheritance
- `*--` - Composition
- `o--` - Aggregation
- `-->` - Association
- `--` - Link (solid)
- `..>` - Dependency
- `..|>` - Realization

## 4. State Diagrams

Show state transitions.

```
```mermaid
stateDiagram-v2
    [*] --> Draft
    Draft --> Review: Submit
    Review --> Approved: Accept
    Review --> Draft: Reject
    Approved --> Published: Publish
    Published --> [*]
```
```

## 5. Entity Relationship Diagrams (ERD)

Show database relationships.

```
```mermaid
erDiagram
    CUSTOMER ||--o{ ORDER : places
    CUSTOMER {
        string name
        string email
        int id
    }
    ORDER ||--|{ LINE-ITEM : contains
    ORDER {
        int orderNumber
        date orderDate
    }
    PRODUCT ||--o{ LINE-ITEM : "ordered in"
```
```

### Relationship Types
- `||--||` - One to one
- `||--o{` - One to many
- `}o--o{` - Many to many
- `||--|{` - One to one or many

## 6. Gantt Charts

Show project schedules.

```
```mermaid
gantt
    title Project Timeline
    dateFormat YYYY-MM-DD
    section Phase 1
    Task 1           :a1, 2024-01-01, 30d
    Task 2           :after a1, 20d
    section Phase 2
    Task 3           :2024-02-01, 12d
    Task 4           :24d
```
```

## 7. Pie Charts

```
```mermaid
pie title Distribution
    "Category A" : 386
    "Category B" : 85
    "Category C" : 15
```
```

## 8. Git Graphs

Show git branch history.

```
```mermaid
gitGraph
    commit
    commit
    branch develop
    checkout develop
    commit
    commit
    checkout main
    merge develop
    commit
```
```

## 9. User Journey

```
```mermaid
journey
    title User Experience
    section Morning
      Wake up: 5: User
      Check phone: 3: User
    section Afternoon
      Work: 1: User
      Break: 5: User
```
```

## Tips and Best Practices

1. **Keep it simple** - Don't overcrowd diagrams
2. **Use meaningful labels** - Make node names descriptive
3. **Test your syntax** - Use the Mermaid Live Editor (https://mermaid.live)
4. **Add comments** - Use `%%` for comments
5. **Consistent styling** - Stick to one direction/style per diagram

## Styling (Optional)

Customize colors and styles:

```
```mermaid
graph LR
    A[Start]:::customStyle --> B[End]
    classDef customStyle fill:#f96,stroke:#333,stroke-width:2px
```
```

## Common Issues

- **Syntax errors** - Check for typos in keywords and arrows
- **Missing spaces** - Ensure proper spacing around arrows
- **Unsupported features** - Some platforms use different Mermaid versions
- **Indentation** - Keep consistent indentation for readability

## Resources

- Official Documentation: https://mermaid.js.org/
- Live Editor: https://mermaid.live
- GitHub Support: Works natively in GitHub markdown

---

Happy diagramming!
