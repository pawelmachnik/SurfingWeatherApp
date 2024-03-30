# SurfingApp

## Description
SurfingApp is a Spring Boot application that helps
to select the best surfing location from five different locations,
using weather data available through the Weatherbit.io API.

## Installation Instructions
1. Clone the repository: `git clone https://github.com/pawelmachnik/SurfingWeatherApp.git`
2. Open the project in a Maven-compatible IDE.
3. Build the application: `mvn clean install`
4. Run the application: `mvn spring-boot:run`

## System Requirements
- Java 8 or higher
- Maven

## Usage Instructions
1. Call the `/api/v1/best-location/{date}` endpoint with the appropriate date in `yyyy-MM-dd`
   format and one of the following locations to get the best surfing location for the given day:
    - Jastarnia (Poland)
    - Bridgetown (Barbados)
    - Fortaleza (Brazil)
    - Pissouri (Cyprus)
    - Le Morne (Mauritius)

## Example Of Correct Response
`{
"cityName": "Jastarnia",
"windSpeed": 5.6,
"temperature": 17.9
}`

## Error Messages
- `DATA_NOT_IN_RANGE`: The provided date is not within the allowed range.
- `WEATHER_NOT_FOUND`: No weather data found for the specified location and date.
- `WRONG_DATA`: Incorrect data provided in the request.

## Project Structure
- `com.surfing.surfingapp.client`: Class for communication with the Weatherbit.io API.
- `com.surfing.surfingapp.config`: Application configuration, including Spring configuration files.
- `com.surfing.surfingapp.controller`: Controller handling requests for the best surfing location.
- `com.surfing.surfingapp.exception`: Exceptions handled by the application.
- `com.surfing.surfingapp.exception.handler`: Global exception handling in the application.
- `com.surfing.surfingapp.model`: Data models of the application.
- `com.surfing.surfingapp.model.data`: Additional data models of the application.
- `com.surfing.surfingapp.model.dto`: DTOs for communication with the client.
- `com.surfing.surfingapp.repository`: Repositories for data operations.
- `com.surfing.surfingapp.service`: Services handling business logic of the application.
