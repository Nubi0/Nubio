import axios from 'axios';

const instance = axios.create({
  baseURL: 'https://nubi0.com',
});

instance.interceptors.request.use(
  (config) => {
    const accessToken = localStorage.getItem('accessToken');

    config.headers['Content-Type'] = 'application/json';
    config.headers['Authorization'] = `Bearer ${accessToken}`;

    return config;
  },
  (error) => {
    console.log(error);
    return Promise.reject(error);
  }
);

instance.interceptors.response.use(async (err) => {
  if (err.response?.status === 401) {
    const token = await axios.get('토큰 갱신 url');
    localStorage.setItem('accessToken', token);
    axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
    err.config.headers = {
      'Content-Type': 'application/json',
      Authorization: `Bearer ${token}`,
    };

    const originalResponse = await axios.request(err.config);
    return originalResponse.data.data;
  }
  return Promise.reject(err);
});
