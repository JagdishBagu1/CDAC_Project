import * as React from 'react';
import { useState } from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Box from '@mui/material/Box';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import Snackbar from '@mui/material/Snackbar';
import { Alert } from '@mui/material';
import axios from 'axios'

export default function SignIn() {
  const [ open, setOpen ] = useState(false)

  const handleClose = () => {
    setOpen(false)
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    console.log({
      email: data.get('email'),
      password: data.get('password'),
    });
    let username = data.get('email')
    let password = data.get('password')

    axios.post(process.env.REACT_APP_SERVER_URL+ '/api/auth/login', {username, password}).then(res => {
      console.log(res.data)
      // Write your logic here after successful response
      setOpen(true)

    }).catch(err => {
      console.log("Error ", err)
    })

  };

  return (
    <Container component="main" maxWidth="sm">
      <Snackbar
        anchorOrigin={{ vertical: 'top', horizontal: 'center' }}
        open={open}
        onClose={handleClose}
        autoHideDuration={2000}
      >
      <Alert onClose={handleClose} severity="success" sx={{ width: '100%' }}>
          You are successfully logged In.
        </Alert>
        </ Snackbar>
      <CssBaseline />
      <Box
        sx={{
          marginTop: 8,
          display: 'flex',
          flexDirection: 'column',
          alignItems: 'center',
        }}
      >
        <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
          <LockOutlinedIcon />
        </Avatar>
        <Typography component="h1" variant="h5">
          Sign in
        </Typography>
        <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
          <TextField
            margin="normal"
            required
            fullWidth
            id="email"
            label="Email Address"
            name="email"
            autoComplete="email"
            autoFocus
          />
          <TextField
              margin="normal"
              required
              fullWidth
              name="password"
              label="Password"
              type="password"
              id="password"
              autoComplete="current-password"
            />
          <Button
            type="submit"
            fullWidth
            variant="contained"
            sx={{ mt: 3, mb: 2, py:1.3 }}
          >
            Sign In
          </Button>
        </Box>
      </Box>
    </Container>
  );
}