# Healthcare Billing Backend

A small Kotlin + Micronaut backend that registers patients and doctors, books appointments
and calculates the bill: consultation fee, discount, 12% GST,
and the split between insurance (90%) and patient co-pay (10%).

## Tech stack

- Kotlin 2.4, Java 21
- Micronaut 4.10
- Gradle 9.8
- JUnit 5
- GitHub Actions: builds and runs the tests.

## How to run

```bash
# run all unit tests
./gradlew test

# start the server on http://localhost:8080
./gradlew run
```

## API

| Method | Path | What it does |
|--------|------|--------------|
| POST | `/patients` | Register a patient (returns id and calculated age) |
| POST | `/doctors` | Register a doctor (NPI must be unique) |
| POST | `/appointments` | Book an appointment, status starts as `SCHEDULED` |
| POST | `/appointments/{id}/complete` | Mark an appointment `COMPLETE` |
| POST | `/appointments/{id}/cancel` | Mark an appointment `CANCELLED` |
| GET | `/appointments/{id}/bill` | Calculate the bill for an appointment |

### Example

Register a patient:

```bash
curl -H 'Content-Type: application/json' \
  -d '{"firstName":"Priya","lastName":"Sharma","dateOfBirth":"03/15/1990",
       "insurance":{"bin":"610014","pcn":"MEDDADV","memberId":"M123456"}}' \
  localhost:8080/patients
```

Register a doctor:

```bash
curl -H 'Content-Type: application/json' \
  -d '{"firstName":"Asha","lastName":"Rao","npi":"1234567890",
       "speciality":"CARDIO","practiceStartDate":"06/15/2000"}' \
  localhost:8080/doctors
```

Book an appointment, and get a bill:

```bash
curl -H 'Content-Type: application/json' \
  -d '{"patientId":"<patient id>","doctorNpi":"1234567890","scheduledAt":"2025-06-15T10:30"}' \
  localhost:8080/appointments

curl -X POST localhost:8080/appointments/<appointment id>/complete

curl localhost:8080/appointments/<appointment id>/bill
```