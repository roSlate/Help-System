import axios from 'axios'

// Every request made with `api` goes to the backend at this address
const api = axios.create({
  baseURL: 'http://localhost:8080',
})

export default api
