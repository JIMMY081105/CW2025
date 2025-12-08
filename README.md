# COMP2042 Coursework – Tetris (CW2025)

## Table of Contents

- [GitHub Repositories](#github-repositories)
- [Compilation & Run Instructions](#compilation--run-instructions)
  - [Prerequisites](#prerequisites)
  - [Importing the Project into IntelliJ IDEA](#importing-the-project-into-intellij-idea)
  - [Running the Application with Maven / JavaFX](#running-the-application-with-maven--javafx)
- [Implemented and Working Properly](#implemented-and-working-properly)
  - [Bug Fixes in Coding / Behaviour Corrections](#bug-fixes-in-coding--behaviour-corrections)
  - [New Game Modes & Features](#new-game-modes--features)
  - [UI / UX Improvements](#ui--ux-improvements)
  - [Refactoring & Architecture Changes](#refactoring--architecture-changes)
- [Implemented but Not Working Properly](#implemented-but-not-working-properly)
- [Features Not Implemented](#features-not-implemented)
  - [Out-of-Scope Ideas](#out-of-scope-ideas)
- [New Java Classes](#new-java-classes)
  - [Core Model / Game Logic](#core-model--game-logic)
  - [View / Rendering / Controllers](#view--rendering--controllers)
  - [Data & Configuration](#data--configuration)
- [Modified Java Classes](#modified-java-classes)
  - [Core Model / Game Logic](#core-model--game-logic-1)
  - [Events & DTOs](#events--dtos)
  - [Bricks & Brick Generation](#bricks--brick-generation)
  - [View & Notifications](#view--notifications)
  - [Application Entry](#application-entry)
- [Removed or Replaced Elements](#removed-or-replaced-elements)
- [Unexpected Problems & How I Solved Them](#unexpected-problems--how-i-solved-them)

---

## GitHub Repositories

You can always find the latest version of this project at:

**[https://github.com/JIMMY081105/CW2025](https://github.com/JIMMY081105/CW2025)**

---

## Compilation & Run Instructions

### Prerequisites

Make sure these are installed and configured before running the game:

1. **Java JDK 17 (or later)**
   - Used to compile and run the JavaFX application.

2. **IntelliJ IDEA**
   - Recommended IDE for this coursework. The project is set up as a Maven project.

3. **Maven**
   - The project uses Maven, and a Maven wrapper (`mvnw` / `mvnw.cmd`) is included, so you can either:
     - Use the wrapper (no need to install Maven separately), or
     - Use a system-wide Maven installation.

4. **Git (optional but recommended)**
   - If you want to clone the repository instead of downloading a ZIP.

### Importing the Project into IntelliJ IDEA

1. **Clone or download the repository:**

   ```bash
   git clone https://github.com/JIMMY081105/CW2025.git
   cd CW2025
   ```

   Or download the ZIP from GitHub and extract it.

2. **Open in IntelliJ IDEA**
   - In IntelliJ, go to **File → Open…**
   - Select the folder containing `pom.xml` (the project root).
   - IntelliJ will detect it as a Maven project and import all dependencies automatically.

3. **Set Project SDK**
   - Go to **File → Project Structure → Project**.
   - Set **Project SDK** to **JDK 17**.
   - If JDK 17 is not listed:
     - Click **New…**, locate your JDK 17 installation, then **Apply**.

4. **Ensure Maven is configured (if not using the wrapper)**
   - Go to **File → Settings → Build, Execution, Deployment → Build Tools → Maven**.
   - Either:
     - Use the bundled Maven, or
     - Point to your own Maven installation.

### Running the Application with Maven / JavaFX

You can run the project in two ways.

#### From IntelliJ (recommended)

1. Open the **Maven** tool window (usually on the right side).
2. Expand:
   - **Lifecycle** → you can run `clean` and `compile` if needed.
   - **Plugins → javafx**.
3. Double-click **javafx:run** to start the application.

#### From the Command Line

In the project root (where `pom.xml` is located):

**On Windows:**
```bash
./mvnw clean javafx:run
```

**On macOS / Linux:**
```bash
./mvnw clean javafx:run
```

This uses the Maven wrapper and starts the JavaFX Tetris game.

---

## Implemented and Working Properly

### Bug Fixes in Coding / Behaviour Corrections

#### Consistent row-clearing logic
- When a line is cleared, blocks above fall down.
- If the falling blocks form another complete line, that new line is also cleared in the same move (cascading clears).
- This matches standard Tetris behaviour and ensures all completed rows are scored correctly in one turn.

#### Ghost piece & landing alignment
- The ghost piece (predicted landing position) is now calculated entirely in board grid coordinates instead of raw pixels.
- Alignment issues at the bottom of the board and off-by-one errors have been fixed by:
  - Using `GameConstants` for all dimensions.
  - Respecting `BOARD_HEIGHT` and `BOARD_WIDTH` in all collision checks.

#### Bomb explosion logic
- Bomb bricks now reliably clear a 3×3 area centred on the bomb impact cell.
- Out-of-bounds checks ensure that the explosion does not throw exceptions when bombs land near edges or corners.
- The internal board matrix is updated via a dedicated `BombEffectService`, so the visual state and model stay in sync.

#### Background loading reliability
- Fixed an issue where the first stage background in Explore China sometimes appeared white.
- Background images (both China stages and time-attack stages) are now:
  - Loaded from the classpath using resource URLs.
  - Applied through a layout helper so that timing/order of initialisation is consistent.

---

### New Game Modes & Features

#### 1. Explore China Mode (Stage-based Tetris)

- China-themed "Explore China" mode where each stage corresponds to a province/region.
- `ChinaStageDescriptionProvider` reads a configuration file containing all 30 stages:
  - Stage name (e.g. Stage 1 – Si Chuan).
  - Background image path (e.g. `images/china/1.jpg`).
  - Short description text shown in the China info box.
- `ChinaStageManager`:
  - Tracks the current stage based on score thresholds.
  - Changes background when advancing stages.
  - Adjusts game tick speed so pieces fall faster in later stages.
  - Updates a side-panel with stage name + description.

#### 2. Time Attack Modes (including 5-minute Time Racing)

- Added time-based modes, including:
  - 1-minute
  - 3-minute
  - 5-minute "Time Racing" (longer, more intense run)
- A visible countdown (`timerBox`) shows remaining time.
- When time reaches zero:
  - The game ends and the score is frozen.
  - An end overlay appears with options:
    - Return to the main menu
    - Restart the same time mode
- Time modes use:
  - Separate time-stage background images.
  - Slightly different tick speeds to match the time pressure.

#### 3. Ghost Piece (Predicted Landing Shadow)

- Shows where the current tetromino will land if dropped straight down.
- Ghost piece:
  - Updates whenever the active piece moves or rotates.
  - Is rendered on a dedicated `ghostPane`.
  - Uses a lighter/transparent style so it is clearly a preview, not a real piece.

#### 4. Hard Drop (Spacebar)

- Pressing **SPACE** performs a hard drop:
  - The active piece instantly drops to the lowest valid row.
  - Uses the same "lowest collision-free row" calculation as the ghost piece.
  - Keeps behaviour consistent between the preview and the actual drop.
  - Enables fast, competitive-style play.

#### 5. Bomb Brick & Bomb Toolbar

- Bomb tetrominoes can spawn and be placed like normal pieces.
- When locked, the bomb triggers a 3×3 explosion around its centre.
- A bomb toolbar in the UI:
  - Shows an emoji 💣.
  - Displays the current bomb count.
- `BombEffectService` plus `MatrixOperations`:
  - Apply the explosion to the internal board.
  - Then run normal row-clearing logic on the result.

#### 6. Plus-shaped special brick (after Stage 15 & in 5-minute Time Racing)

- Introduced a new "plus" (+) shaped brick implemented in `PlusBrick` and created via `BrickFactory`.
- **Behaviour:**
  - In Explore China, once the player progresses beyond around Stage 15, the generator starts including the plus-shaped brick.
  - The 5-minute Time Racing mode also enables this brick to increase difficulty.
- **Impact:**
  - Forces players to adapt stacking strategies to a less standard shape.
  - Increases mid–late game complexity without breaking core Tetris feel.

---

### UI / UX Improvements

#### New main menu / mode selection

- New home screen and mode selection screen:
  - Explore China
  - Time Racing / Time Attack
- Mode buttons have a hover effect:
  - Slight expansion and highlight, inspired by modern Tetris UIs (e.g. tert.io-style "stretch on hover").

#### Game screen layout

The main game screen (`gamepanel.fxml` + `GameScreenController`) is organised as:

- **Centre – playfield**
  - `gameBoard`, `gamePanel`, `brickPanel`, `ghostPane`
- **Right – side panel**
  - Current score
  - Best score (for this run/mode)
  - Next 3 pieces preview
  - Pause button
  - Exit Game button
- **Left – China description box (in Explore China mode)**
  - Stage title + descriptive text
- **Top / overlay – timer (in Time Attack modes)**

#### Consistent styling via CSS (`window_style.css`)

Unified stylesheet for:

- Panel frames (`.sectionBox`)
- Board border (`.gameBoard`)
- Next queue boxes (`.nextBrick` etc.)
- Mode selection buttons (`.mode-button`, `.mode-title`, `.mode-subtitle`, `.mode-badge`)
- Pause/Exit buttons (`.pause-button`)
- China description box (`.china-box`, `.china-title`, `.china-description`)
- End overlay (`.end-overlay`, `.end-title`, `.end-subtitle`, `.end-actions`)
- Bomb toolbar (`.bombToolbarBox`, `.bombEmojiLabel`, `.bombCountBadge`)
- A digital-style font is used for scores and main titles to match the retro arcade feel.

#### End-of-game overlay

Instead of jumping to a separate scene, a stacked overlay appears on top of the game:

- Darkened background (with support for video/audio if enabled).
- Title (e.g. "Game Over", "Time Up").
- Subtitle text.
- Two big buttons:
  - Back to Mainboard
  - New Game (restart current mode)

---

### Refactoring & Architecture Changes

#### Package restructuring

Project now has clearer package boundaries, for example:

- **`com.comp2042.model`**
  - Board, bricks, score, managers like `ChinaStageManager`, `TimeAttackManager`, `BombManager`, `GameSessionManager`.

- **`com.comp2042.view.screen`**
  - Screen controllers (`HomeController`, `ModeSelectionController`, `GameScreenController`).

- **`com.comp2042.view.render`**
  - Rendering/layout helpers (`BoardRenderer`, `NextBricksRenderer`, `GameOverPanel`, `BoardVibrationEffect`, `GameLayoutManager`).

- **`com.comp2042.data`**
  - Small data/DTO classes (`ViewData`, `DownData`, `ClearRow`, stage descriptions).

- **`com.comp2042.util`**
  - Utilities (`GameConstants`, `MatrixOperations`, `BlockTextureProvider`, `GameConfig`, `LayoutMetrics`, `BackgroundMusicManager`).

- **`com.comp2042.event`**
  - Input events and listeners (`MoveEvent`, `EventType`, `EventSource`, `InputEventListener`, `GameInputHandler`).

This makes the project closer to a **layered architecture** (model / view / controller / utilities) and directly addresses high coupling in the original code.

#### Board interface and implementation

**Board (interface):**
- Defines the public contract of a playable Tetris board:
  - Move / rotate / drop the active piece.
  - Hard drop.
  - Apply bomb explosions.
  - Query ghost landing position.
  - Get `ViewData` for rendering.
  - Expose score and game-over state.
- Makes it possible (in principle) to plug in a different board implementation (e.g. for testing or future modes) without changing the UI.

**SimpleBoard (implementation):**
- Owns the matrix and active piece (via `ActivePiece`).
- Handles collision detection, line clearing and bomb effects.
- Talks only in terms of grid coordinates, not JavaFX nodes.
- Respects **Single Responsibility Principle** by delegating:
  - Bomb logic to `BombEffectService`.
  - Stage progression to `ChinaStageManager`.
  - Time logic to `TimeAttackManager`.
  - Layout to renderers instead of directly drawing.

#### Rendering vs logic separation

**BoardRenderer:**
- Takes `ViewData` and draws it into `GridPane` / `Pane` objects.
- Contains no game rules – only rendering concerns.
- This clear split supports the **MVC / layered design** emphasised in the module.

**NextBricksRenderer:**
- Renders the next three tetrominoes into a vertical list/VBox.
- Consumes a simple view representation (e.g. `NextShapeInfo`) rather than reaching into the model.

**Screen controllers** (`HomeController`, `ModeSelectionController`, `GameScreenController`):
- Coordinate:
  - Model (`Board`, managers).
  - View (FXML controls).
  - Renderers.
- They forward input events via `GameInputHandler` and listen to model changes, instead of mixing everything into one giant controller.

#### Centralised game constants

Most magic numbers were removed from scattered classes and moved into:
- `GameConstants` (core sizes & timings).
- `LayoutMetrics` (derived paddings, panel sizes, margins).

This significantly improves **configurability and maintainability**: changing board size, speed or spacing now requires editing a small number of well-documented constants rather than hunting through arbitrary code.

#### Events and input handling

Introduced a small event layer:
- `MoveEvent`, `EventType`, `EventSource`, `InputEventListener`
- `GameInputHandler` maps JavaFX key codes to these events.

Keyboard input is now expressed as **semantic actions**:
- `LEFT`, `RIGHT`, `DOWN`, `ROTATE`, `HARD_DROP`, `PAUSE`, etc.

`SimpleBoard` and managers respond to these actions, making the code clearer and easier to extend (e.g. adding a "soft drop" or "hold" in the future).

#### Game loop & session management

**GameLoop** encapsulates the JavaFX Timeline logic:
- Ticks the board.
- Updates the timer.
- Notifies renderers.

**GameSessionManager** owns which mode is running (Explore China / Time Attack, including 5-minute Time Racing), how to restart, and how to navigate back to menus.

**NotificationManager / GameNotificationManager** coordinate in-game messages (e.g. "Stage Up!", "Time Up!"), making feedback more consistent and easy to adjust.

---

## Implemented but Not Working Properly

#### Layout on unusual resolutions

- The layout is tuned for a standard 1080p window.
- On very extreme aspect ratios some panels can look slightly cramped or further apart than intended, but the game remains playable.
- These are visual layout quirks only – gameplay logic (board size, collisions, scoring) remains correct.

---

## Features Not Implemented

### Planned but Not Implemented

#### Hold / swap piece mechanic
- Considered adding a "hold" slot to store one tetromino and swap, but this required extra UI plus additional model logic.
- Dropped due to time and scope constraints to prioritise ghost, bombs, China stages, time modes and plus brick.

#### Persistent high-score storage
- Best scores are currently session-only.
- Persisting to file/DB was not implemented so that refactoring and gameplay features remained the main focus.
- Adding a sound of clear row

### Out-of-Scope Ideas

- Online leaderboard / multiplayer.

---

## New Java Classes

"New" here means classes that did not exist in the original CW2025 template and were created as part of this refactoring & extension. They mainly aim to split responsibilities, apply design patterns, and prepare the code for future extension.

### Core Model / Game Logic

#### ActivePiece (`com.comp2042.model`)

- Represents the currently falling tetromino (shape, rotation, grid position).
- Encapsulates movement and rotation logic so that `SimpleBoard` no longer has to track raw coordinates for every brick cell.
- This separation makes it easier to:
  - Implement new movement rules (e.g. wall kicks).
  - Reuse the same movement logic across modes.

#### BombEffectService (`com.comp2042.model`)

- A pure stateless service that applies a 3×3 explosion around a bomb cell to a board matrix.
- Fully separates bomb rules from board infrastructure:
  - `SimpleBoard` asks for an updated matrix.
  - `BombEffectService` handles all the explosion details and safety checks.
- This follows **Single Responsibility Principle** and makes bomb behaviour easier to test and reason about in isolation.

#### BrickFactory (`com.comp2042.model.brick`)

- Central factory for creating brick instances:
  - Standard tetrominoes (I, J, L, O, S, T, Z).
  - Extended shapes such as `PlusBrick`.
- This replaces scattered `new SomeBrick()` calls with a single creation point:
  - Makes the code follow the **Factory pattern**.
  - Makes it trivial to plug in new shapes without changing game logic everywhere.

#### PlusBrick (`com.comp2042.model.brick`)

- Implements the new plus-shaped tetromino introduced after Stage 15 and in 5-minute Time Racing mode.
- Designed to integrate cleanly with the existing brick hierarchy (rotations, grid representation).
- Demonstrates that the brick model is extensible and not hard-coded to only support the original seven shapes.

#### GameConfig (`com.comp2042.util`)

- Holds high-level configuration data such as:
  - Stage score thresholds.
  - Time limits for Time Attack modes (1/3/5 minutes).
  - Mode-specific tuning.
- Moves "magic configuration" out of controllers, which improves cohesion and makes adjusting game balance a configuration change, not a code change.

#### LayoutMetrics (`com.comp2042.util`)

- Computes layout-related values (panel sizes, paddings, offsets) based on `GameConstants`.
- Used by renderers and layout managers to keep visual spacing consistent.
- Encapsulates layout maths so that the board can be rescaled or repositioned by editing one place.

#### GameInputHandler (`com.comp2042.event` or `com.comp2042.input`)

- Dedicated component for mapping JavaFX key events to `MoveEvent` / `EventType` values.
- Prevents controllers and the model from depending directly on key codes.
- Makes adding or rebinding keys straightforward and supports **Open/Closed Principle** (extend behaviour via new events, not by modifying model internals).

#### GameLoop (`com.comp2042.model` / `util`)

- Wraps the JavaFX timing mechanism (e.g. `Timeline`) responsible for stepping the game:
  - Advances the board.
  - Updates the timer in Time Attack mode.
  - Triggers re-renders.
- Extracting the loop into its own class keeps controllers thinner and makes the update rhythm easy to adjust or pause.

#### BoardVibrationEffect (`com.comp2042.view.render`)

- Handles subtle shake animations on the board when:
  - A line is cleared.
  - A bomb explodes.
- This effect is kept separate from game logic and purely in the view layer, which helps maintain a clean separation between visuals and rules.

#### BackgroundMusicManager (`com.comp2042.util` / `audio`)

- Manages background music:
  - Start/stop/pause.
  - Handles transitions between menu, in-game and time attack music.
- Centralising this avoids duplicated audio code in multiple controllers and respects the **DRY principle**.

#### BackgroundVideoManager (`com.comp2042.view` / `util`)

- Handles optional video backgrounds/overlays (e.g. for the end-of-game layer).
- Encapsulates resource loading, scaling and cleanup so that controllers do not need to manage low-level media APIs.

#### BombManager (`com.comp2042.model` / `controller`)

- Tracks the player's bomb count and coordinates:
  - When bomb bricks are granted.
  - How they are consumed.
- Keeps bomb inventory separate from score and board logic, reducing coupling and clarifying responsibilities.

#### ChinaStageManager (`com.comp2042.model`)

- Controls Explore China progression:
  - Tracks current stage index.
  - Triggers background changes.
  - Applies speed adjustments.
- Works with `ChinaStageDescriptionProvider` and `GameLayoutManager` to connect data → logic → visuals.

#### GameLayoutManager (`com.comp2042.view.render`)

- Applies background images and layout tweaks based on:
  - Current mode (Explore China / Time Attack).
  - Current stage or time phase.
- Removes ad-hoc background setting from controllers, centralising all layout composition in a single helper.

#### GameNotificationManager & NotificationManager (`com.comp2042.view` / `util`)

- Coordinate in-game notifications such as:
  - "Stage Up!"
  - "Time Up!"
  - Instructional or hint messages.
- Implement a simple internal pipeline for queueing, displaying and fading notifications using `NotificationPanel`.

#### GameSessionManager (`com.comp2042.model` / `controller`)

- Knows which mode is currently active and how to transition between:
  - Home → Mode Selection → Game → End overlay → Home.
- Prevents screen controllers from having to understand the full navigation flow, improving separation of concerns.

#### TimeAttackManager (`com.comp2042.model` / `controller`)

- Central owner of Time Attack behaviour:
  - Counts down from the configured duration (1, 3, 5 minutes).
  - Notifies when time is up.
  - Triggers game-over logic and overlays.
- This isolates time-based concerns from generic board logic.

### View / Rendering / Controllers

#### BoardRenderer (`com.comp2042.view.render`)

- Converts `ViewData` and board state into actual JavaFX Nodes.
- Knows how to:
  - Draw occupied cells.
  - Draw the ghost piece.
  - Respect `GameConstants` and `LayoutMetrics` for correct alignment.
- This is a key step towards a proper **Model–View separation**.

#### NextBricksRenderer (`com.comp2042.view.render`)

- Draws the next 3 pieces using simple data from the brick queue.
- Encapsulates layout and styling of the preview without polluting controllers.

#### GameScreenController (`com.comp2042.view.screen`)

- Main controller for the in-game screen:
  - Wires together `Board`, managers (China, TimeAttack, Bomb), renderers and UI controls.
  - Handles pause/resume and restart actions.
- Compared to the original monolithic controller, its responsibility is now limited to wiring and orchestration, not low-level logic.

#### HomeController (`com.comp2042.view.screen`)

- Controls the home / main menu screen:
  - Responds to "Explore China", "Time Racing", "Exit".
  - Delegates to `GameSessionManager` for mode switching.
- Keeps the menu logic self-contained.

#### ModeSelectionController (`com.comp2042.view.screen`)

- Handles selection of:
  - Explore China vs Time Attack.
  - Time Attack durations (1/3/5 minutes).
- Ensures transitions remain consistent and clearly separated from gameplay logic.

#### HomeSelection (`com.comp2042.view` / `data`)

- A small helper to represent which mode/time-option the player chose.
- Makes the code more expressive and avoids passing around raw strings or enums between controllers.

### Data & Configuration

#### ChinaStageDescriptionProvider (`com.comp2042.data`)

- Loads stage metadata (name, description, background, thresholds) from configuration files.
- Exposes a clean API for `ChinaStageManager` to retrieve stage information.
- This is an example of externalising configuration and avoiding hard-coded province data in the code.

#### BlockTextureProvider (`com.comp2042.util`)

- Central provider for block colours/gradients/textures.
- Ensures visual consistency and makes it easier to restyle the game without touching logic.

---

## Modified Java Classes

These classes existed in the original template but have been substantially refactored to improve layering, readability and extensibility.

### Core Model / Game Logic

#### Board

- Originally a low-level interface tied quite closely to implementation details.
- Now clearly describes the public behaviour of a Tetris board:
  - Movement, rotation, dropping, hard drop, bombs, ghost queries.
- Designed so additional implementations (e.g. AI training board or test double) could be introduced without altering the rest of the code.

#### SimpleBoard

- Was previously responsible for a mix of:
  - Board state.
  - Piece generation.
  - Row-clearing.
  - Some UI-influenced behaviour.
- Now:
  - Focuses solely on game rules and state transitions.
  - Delegates:
    - Movement details to `ActivePiece`.
    - Explosions to `BombEffectService`.
    - Stage/time behaviour to dedicated managers.
  - Is independent of JavaFX and rendering, which greatly improves testability and adherence to **Single Responsibility Principle**.

#### Score

- Previously just a number updated in a few places.
- Now:
  - Encapsulates all score mutations in one place.
  - Exposes an observable property so the UI can bind to it.
  - Guarantees that line clears, bombs, and potentially hard drops are applied consistently.

#### MatrixOperations

- Originally a collection of utility methods that were tightly coupled to one specific board usage.
- Now:
  - Provides reusable, pure functions for matrix manipulation:
    - Line-clear detection.
    - Ghost landing row computation.
    - Explosion region resolution.
  - This makes it easier to build tests that verify core Tetris logic without any UI.

#### GameController

- Formerly the "god class" responsible for:
  - Handling input.
  - Managing board state.
  - Drawing.
  - Managing game over.
- As part of the refactor:
  - Its responsibilities were split across:
    - `GameScreenController` (screen-level wiring).
    - `GameLoop` (timing).
    - `GameInputHandler` (input mapping).
    - Managers (`GameSessionManager`, `ChinaStageManager`, `TimeAttackManager`).
  - The leftover `GameController` role is much thinner and focused on high-level coordination, significantly reducing complexity.

### Events & DTOs

#### EventSource, EventType, MoveEvent, InputEventListener

- Extended from simple key wrappers into a small event system:
  - Now support extra actions (`HARD_DROP`, `PAUSE`, `BOMB` triggers).
  - Provide clearer semantics for what the player is trying to do.
- This decouples "what happens when a key is pressed" from "how the board reacts", which is a key refactoring objective.

#### DownData, ClearRow, ViewData

- Were originally just simple containers.
- Now:
  - Represent well-defined contracts between the model and the rendering layer.
  - Support the separation where the board supplies abstract data and the renderer decides how it should look.

### Bricks & Brick Generation

#### Brick, IBrick, JBrick, LBrick, OBrick, SBrick, TBrick, ZBrick

- Cleaned up to:
  - Reduce duplication of shape and rotation definitions.
  - Work nicely with `BrickFactory`.
- Their refactoring demonstrates that the brick model is modular and reusable instead of being a hard-coded one-off.

#### BrickRotator

- Refactored to encapsulate rotation rules explicitly:
  - Handles transformations in a single place.
  - Helps avoid subtle rotation bugs at the edges.
- This simplifies both `SimpleBoard` and `ActivePiece`.

#### BrickGenerator, RandomBrickGenerator

- Updated from a very straightforward random generator to one that:
  - Integrates with `BrickFactory`.
  - Is aware of extended bricks like `PlusBrick`.
  - Could be enhanced later (e.g. "bag" systems, difficulty scaling) without rewriting the whole generator.

#### NextShapeInfo

- Moved under the brick package and reworked to be a clear data carrier for next-piece previews.
- Makes it easier for `NextBricksRenderer` to consume upcoming bricks without reaching into the board's internals.

### View & Notifications

#### GameOverPanel

- Previously just a small UI element.
- Now redesigned as a flexible component used inside the end overlay:
  - Shows context-aware messages (Game Over, Time Up).
  - Offers consistent actions (Back to Mainboard, New Game).
- Helps keep end-of-game behaviour visually consistent across modes.

#### NotificationPanel

- Reworked to integrate with `NotificationManager` / `GameNotificationManager`.
- Responsible only for displaying messages; decision-making about which messages to show lives in the managers.

### Application Entry

#### Main

- Previously launched directly into a single game screen.
- Now:
  - Starts from `HomeController` (home/menu).
  - Sets up shared infrastructure (music, layout, session manager).
  - Then navigates to gameplay depending on user choice (Explore China, Time Attack, etc.).
- This brings the application structure closer to a real-world JavaFX application with multiple scenes and a central entry point.

---

## Removed or Replaced Elements

#### Monolithic controller logic

- The original "do everything" controller has been replaced with:
  - Multiple smaller controllers.
  - Clear renderers.
  - Focused managers.
- This significantly reduces class size and improves adherence to **SRP** and **low coupling**.

#### Inline layout constants

- Hard-coded pixel values scattered across the codebase have been removed.
- `GameConstants` and `LayoutMetrics` now define:
  - Board size.
  - Brick size and gaps.
  - Side panel proportions.
- This makes future UI changes far safer and more maintainable.

#### Ad-hoc background handling

- Background images were originally set directly in controllers.
- This has been replaced by:
  - `ChinaStageManager` + `GameLayoutManager` for Explore China.
  - Time Attack background handling for time modes.
- The result is a cleaner separation between:
  - Data (which stage/time we're in),
  - Logic (what that means),
  - Presentation (what background is shown).

---

## Unexpected Problems & How I Solved Them

### 1. First Background in Explore China Appearing White

| Problem | Cause | Fix |
|---------|-------|-----|
| The first time I entered Explore China, the board background sometimes showed as a plain white rectangle. | Stage manager and layout initialisation order was wrong. Sometimes the background was requested before the JavaFX scene and resources were fully ready. | Ensured `ChinaStageManager.enableExploreMode()` runs only after layout objects are created. Centralised background loading in `GameLayoutManager`, which resolves the resource URL and applies the image consistently, logging any failures. |

### 2. Ghost Piece & Bomb Explosion Offset

| Problem | Cause | Fix |
|---------|-------|-----|
| Ghost piece and bomb explosion preview occasionally looked slightly shifted to the left or off the correct row. | Mixed use of pixel coordinates and grid coordinates, plus inconsistent use of `BRICK_SIZE` and `GRID_GAP`. | Switched entirely to grid-based calculations in the model. Rendering layer converts from grid → pixels using a single shared formula. Added bounds checks to prevent drawing outside the board. |

### 3. Event / Input Handling Getting Messy

| Problem | Cause | Fix |
|---------|-------|-----|
| As I added new actions (hard drop, bombs, pause), the JavaFX key handling code became tangled and hard to maintain. | Board logic depended directly on key codes inside the controller. | Introduced `MoveEvent` / `EventType` / `InputEventListener` plus `GameInputHandler`. The controller now just maps keys to high-level events. The board only knows about actions like `LEFT`, `RIGHT`, `ROTATE`, `HARD_DROP`, `PAUSE`, etc. |

### 4. Managing Many New Classes Near the Deadline

| Problem | Fix / Lessons Learned |
|---------|------------------------|
| With ghosts, bombs, China stages, time modes, plus brick, and new screens, it was easy for the code to drift back into "big ball of mud" territory. | I enforced clear package boundaries, used interfaces (`Board`) and dedicated managers (`ChinaStageManager`, `TimeAttackManager`, `GameSessionManager`, `GameLayoutManager`), and tried to keep each new class focused on a single responsibility. This made late-stage changes safer and more predictable even when time was tight. |

---

**End of README**
