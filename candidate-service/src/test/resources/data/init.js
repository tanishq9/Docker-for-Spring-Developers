// create database
db = db.getSiblingDB('candidatedb');

// create user
db.createUser({
    user: 'candidate_user',
    pwd: 'candidate_password',
    roles: [
        {
            role: "readWrite",
            db: "candidatedb"
        }
    ]
})

// create collection
db.createCollection('candidate_collection')

// create documents
db.candidate_collection.insertMany([
    {
        "_id": "1",
        name: "ab",
        skills: ["java"]
    },
    {
        "_id": "2",
        name: "cd",
        skills: ["java", "jira"]
    },
    {
        "_id": "3",
        name: "yz",
        skills: ["agile", "jira"]
    },
    {
        "_id": "4",
        name: "pq",
        skills: ["java", "jira", "project"]
    }
])