# App Registry

Master tracker for all apps built. Check this before starting a new app to avoid duplicates and to reuse components.

---

## Built Apps

| # | App Name | Repo | Category | Key Components | Play Store | Date |
|---|----------|------|----------|---------------|------------|------|
| 1 | Unit Converter | [app-unit-converter](https://github.com/ab069/app-unit-converter) | Utility | ConverterCard, Converter.kt, BottomNav tabs | Pending | 2026-05-09 |

---

## Reusable Components

Components extracted from previous apps that can be copied into new apps.

| Component | File | Found In | What It Does |
|-----------|------|----------|--------------|
| ConverterCard | `ui/components/ConverterCard.kt` | app-unit-converter | Input + two dropdowns + result card. Reuse for any unit/conversion UI |
| Converter logic | `converter/Converter.kt` | app-unit-converter | Pure Kotlin conversion math with formatting |

---

## App Ideas Queue

Priority-ordered list of apps to build next. Cross off as done.

### Utility
- [ ] Tip Calculator with bill split
- [ ] QR Code Generator
- [ ] Countdown Timer to any date
- [ ] Password Generator
- [ ] Flashlight + SOS mode
- [ ] Random Decision Maker

### Health
- [ ] Water Intake Tracker
- [ ] Sleep Logger
- [ ] Mood Journal
- [ ] Meditation Timer
- [ ] Period Tracker

### Productivity
- [ ] Habit Tracker with streaks
- [ ] Pomodoro Timer
- [ ] Note app with categories
- [ ] Bill Reminder
- [ ] Reading List Tracker

### Finance
- [ ] Expense Tracker
- [ ] Savings Goal Tracker
- [ ] Subscription Tracker
- [ ] Budget Planner
- [ ] Debt Payoff Calculator

---

## Rules

1. Always check this file before starting a new app — no duplicates
2. After building, add the app to the Built Apps table
3. If you extract a reusable component, add it to the Reusable Components table
4. Cross off the idea from the queue once the repo is created
