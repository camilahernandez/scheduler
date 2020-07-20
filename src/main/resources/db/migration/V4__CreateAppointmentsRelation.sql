CREATE TABLE IF NOT EXISTS physician (
    physician_id SERIAL PRIMARY KEY, 
    speciality VARCHAR(25) NOT NULL,
    years_of_experience INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS user_appointment (
    fk_physician_id INTEGER NOT NULL REFERENCES physician(physician_id),
    fk_person_id UUID NOT NULL REFERENCES person(id),
    appointment_date TIMESTAMP NOT NULL
);


