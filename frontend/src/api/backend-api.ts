import axios, { AxiosResponse } from 'axios'

const axiosApi = axios.create({
  baseURL: '/api',
  timeout: 1000,
  headers: { 'Content-Type': 'application/json' }
})

interface Client {
  id: number;
  firstName: string;
  lastName: string;
  email : string;
  balance : number;
  overdraft : number;

}

export default {
  hello (): Promise<AxiosResponse<string>> {
    return axiosApi.get('/hello')
  },
  getClient (userId: number): Promise<AxiosResponse<Client>> {
    return axiosApi.get('/user/' + userId)
  },
  createClient (firstName: string, lastName: string): Promise<AxiosResponse<number>> {
    return axiosApi.post('/user/' + firstName + '/' + lastName)
  },
  getSecured (user: string, password: string): Promise<AxiosResponse<string>> {
    return axiosApi.get('/secured/', {
      auth: {
        username: user,
        password: password
      }
    })
  }
}
