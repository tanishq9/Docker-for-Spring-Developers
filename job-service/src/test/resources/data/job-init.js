// create database
db = db.getSiblingDB('jobdb');

// create user
db.createUser({
    user: 'job_user',
    pwd: 'job_password',
    roles: [
        {
            role: "readWrite",
            db: "jobdb"
        }
    ]
})

// create collection
db.createCollection('job_collection')

// create documents
db.job_collection.insertMany([
    {
        description: "Senior Dev Java",
        company: "abc",
        skills: ["java", "spring", "docker"],
        salary: 100000,
        isRemote: false
    },
    {
        description: "Junior Dev Java",
        company: "abc",
        skills: ["java"],
        salary: 50000,
        isRemote: false
    },
    {
        description: "Scrum master",
        company: "abc",
        skills: ["agile", "jira"],
        salary: 60000,
        isRemote: true
    },
    {
        description: "Director of Eng.",
        company: "abc",
        skills: ["java", "jira", "project"],
        salary: 150000,
        isRemote: true
    }
])