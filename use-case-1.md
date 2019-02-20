# USE CASE: 1 Produce a Population Report.

## CHARACTERISTIC INFORMATION

### Goal in Context

As a *Data Analyst* I want *to produce a population report from the database.*

### Scope

Company.

### Level

Primary task.

### Preconditions

We know the role.  Database contains relevant data.

### Success End Condition

A population report is produced for the Data Analyst to use.

### Failed End Condition

No report is produced.

### Primary Actor

Data Analyst.

### Trigger

A request for a population report is sent to the Data Analyst.

## MAIN SUCCESS SCENARIO

1. Employee requests population report.
2. Data Analyst obtains necessary columns required for report.
3. Data Analyst extracts population data from database.
4. Data Analyst provides population report.

## EXTENSIONS

3. **Data does not exist**:
    1. Data Analyst informs Employee the data does not exist.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 4.0
