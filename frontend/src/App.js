import '@fontsource/roboto/300.css';
import '@fontsource/roboto/400.css';
import '@fontsource/roboto/500.css';
import '@fontsource/roboto/700.css';
import Home from './pages/Home';
import SignIn from './pages/SignIn';
import Register from './pages/Register';
import Profile from './pages/Profile';

import { BrowserRouter, Routes, Route } from "react-router-dom";
import BlogDetail from './pages/BlogDetail';
import AddBlog from './pages/AddBlog';
import Error from './pages/Error';
import Footer from './components/Footer';
import { Box, CssBaseline } from '@mui/material';
import Logout from './pages/Logout';

function App() {
  return (
    <>
      <CssBaseline />
      <BrowserRouter>
        <Box
          component="main"
          sx={{
            minHeight: '100vh',
            display: 'flex',
            flexDirection: 'column',
          }}
        >
          <Routes>
            <Route path='/' element={<Home />} />
            <Route path='/register' element={<Register />} />
            <Route path='/signIn' element={<SignIn />} />
            <Route path='/logout' element={<Logout />} />
            <Route path='/profile' element={<Profile />} />
            <Route path='/addBlog' element={<AddBlog />} />
            <Route path='/blogDetails/:id' element={<BlogDetail />} />

            <Route path='/*' element={<Error />} />
          </Routes>
          <Footer />
        </Box>
      </BrowserRouter>
    </>
  );
}

export default App;
