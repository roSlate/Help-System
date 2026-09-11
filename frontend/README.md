# Help System — Frontend

Internal Q&A system for the Specialisterne training project. Employees can post questions and other employees can
answer them.

## Status: early setup, this document will change a lot throughout the following days, most likely

## Prerequisites so far

- Node.js (with npm)

### Installing Node.js

Recommended: install via [nvm](https://github.com/nvm-sh/nvm) (nvm-windows on Windows: https://github.com/coreybutler/nvm-windows)
so everyone can easily match the same version instead of relying on whatever installer version they grabbed.

```bash
nvm install 22
nvm use 22
```

Alternatively, download directly from https://nodejs.org (LTS version).

## Tech stack

Generated via Vite with the React template:

**Vite**: our build tool, runs the dev server and bundles the app for production;
**React 19**: core UI library;
**React Router**: client-side routing between pages.

### Dependencies (subject to change):

**react-router**: maps URL paths to pages;
**lucide-react**: icon set matching the style sheet (layout-dashboard, database, chart-column, folder, clock, smile,
check-check, paperclip, trending-down, trending-up);
**vitest + @testing-library/react + @testing-library/jest-dom + jsdom**: testing setup (installed, not yet wired
into any test file);
**oxlint**: linting, bundled by the Vite template.

## Project structure

**Pages vs blocks**: similar idea to WordPress blocks — a page is a route-level component that mounts one or more
blocks; a block is a small reusable piece of UI (header, footer, etc.) that any page can use.

src
|-pages (route-level components, one per page, wired up in App.jsx)
|-blocks (reusable UI pieces, composed together inside pages)
|-style.css (design tokens: colors, fonts, sizing classes, pulled from the style sheet)
|-App.jsx (routes -> pages)
|-main.jsx (entry point, mounts App inside BrowserRouter)

(only `Home` page and `Header`/`Footer` blocks exist so far, as the pattern to follow for new ones)

## Setup

```bash
cd frontend
npm install
npm run dev
```

Runs at `http://localhost:5173` by default.

## Style sheet

Colors, fonts (Sora for headings, Rubik for body) and icon set are defined in `src/style.css` as CSS custom
properties and utility classes (`.sora-title`, `.rubik-text`, `.icon`, `.status`, etc.), matching the project's
design sheet.
