import React, { useState } from "react";
import Avatar from "@mui/material/Avatar";
import Button from "@mui/material/Button";
import CssBaseline from "@mui/material/CssBaseline";
import TextField from "@mui/material/TextField";
import Box from "@mui/material/Box";
import LockOutlinedIcon from "@mui/icons-material/LockOutlined";
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import Snackbar from "@mui/material/Snackbar";
import { Alert } from "@mui/material";
import { Link } from "react-router-dom";
import Grid from "@mui/material/Grid";
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import Select from '@mui/material/Select';
import axios from "axios";
import { useNavigate } from "react-router-dom";

function Register() {
  const [open, setOpen] = useState(false);
  const [gender, setGender] = useState('');
  const [errorMessage, setErrorMessage] = useState('');
  const [messageStatus, setMessageStatus] = useState(true);

  const navigate = useNavigate();

  const handleClose = () => {
    setOpen(false);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    setOpen(false);
    const data = new FormData(event.currentTarget);

    let firstname = data.get("firstName");
    let lastname = data.get("lastName");
    let email = data.get("email");
    let password = data.get("password");
    let dateOfBirth = data.get("dateOfBirth");
    let gender = data.get("gender");

    const jsonData = {
      email: email,
      firstName: firstname,
      lastName: lastname,
      password: password,
      dateOfBirth: dateOfBirth,
      gender: gender
    };

    console.log(jsonData);

    axios
      .post(process.env.REACT_APP_SERVER_URL + "/api/auth/register", jsonData)
      .then((res) => {
        console.log(res.data);
        // Write your logic here after successful response
        setOpen(true);
        setMessageStatus(true);
        localStorage.setItem('token', res.data.token);
        navigate('/profile');

      })
      .catch((err) => {
        console.log("Error ", err.response.data.message);
        if (!err.response.data.success) {
          console.log("unsuccessful registration");
          setErrorMessage("Unsuccessful Registration");
          setOpen(true);
          setMessageStatus(false);
        }
      });
  };

  return (
    <Container
      maxWidth="sm"
      sx={{
        flexGrow: 1,
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'center',
      }}
    >
      <Snackbar
        anchorOrigin={{ vertical: "top", horizontal: "center" }}
        open={open}
        onClose={handleClose}
        autoHideDuration={2000}
      >
        <Alert onClose={handleClose} severity={messageStatus ? "success" : "error"} sx={{ width: "100%" }}>
          {errorMessage}
        </Alert>
      </Snackbar>
      <CssBaseline />
      <Box
        sx={{
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
        }}
      >
        <Avatar sx={{ m: 1, bgcolor: "secondary.main" }}>
          <LockOutlinedIcon />
        </Avatar>
        <Typography component="h1" variant="h5">
          Register
        </Typography>
        <Box component="form" noValidate onSubmit={handleSubmit} sx={{ mt: 3 }}>
          <Grid container spacing={2}>
            <Grid item xs={12} sm={6}>
              <TextField
                autoComplete="given-name"
                name="firstName"
                required
                fullWidth
                id="firstName"
                label="First Name"
                autoFocus
              />
            </Grid>
            <Grid item xs={12} sm={6}>
              <TextField
                required
                fullWidth
                id="lastName"
                label="Last Name"
                name="lastName"
                autoComplete="family-name"
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                required
                fullWidth
                id="email"
                label="Email Address"
                name="email"
                autoComplete="email"
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                required
                fullWidth
                name="password"
                label="Password"
                type="password"
                id="password"
                autoComplete="new-password"
              />
            </Grid>
            <Grid item xs={12} sm={6}>
              <InputLabel id="demo-simple-select-label">Gender</InputLabel>
              <Select
                fullWidth
                labelId="demo-simple-select-label"
                id="demo-simple-select"
                label="Gender"
                name="gender"
                value={gender}
                onChange={(e) => { setGender(e.target.value); }}
              >
                <MenuItem value={"Male"}>MALE</MenuItem>
                <MenuItem value={"Female"}>FEMALE</MenuItem>
                <MenuItem value={"Others"}>Others</MenuItem>
              </Select>
            </Grid>
            <Grid item xs={12} sm={6}>
              <InputLabel id="demo-simple-select-label">Date Of Birth</InputLabel>
              <TextField
                required
                fullWidth
                name="dateOfBirth"
                type="date"
                id="dob"
                autoComplete="off"
              />
            </Grid>
          </Grid>
          <Button
            type="submit"
            fullWidth
            variant="contained"
            sx={{ mt: 3, mb: 2, py: 1.3 }}
          >
            Register
          </Button>
          <Grid container justifyContent="flex-end">
            <Grid item>
              <Link to="/signin" variant="body2">
                Already have an account? Sign in
              </Link>
            </Grid>
          </Grid>
        </Box>
      </Box>
    </Container>
  );
}

export default Register;
