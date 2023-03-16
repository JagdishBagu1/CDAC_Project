import { Alert, Box, Button, Container, CssBaseline, Grid, InputLabel, MenuItem, Select, Snackbar, TextField, Typography } from "@mui/material";
import axios from "axios";
import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

export default function AddBlog() {
  const [open, setOpen] = useState(false);
  const [messageStatus, setMessageStatus] = useState(true);
  const [errorMessage, setErrorMessage] = useState('');
  const [category, setCategory] = useState('');

  const navigate = useNavigate();

  const handleClose = () => {
    setOpen(false);
  };

  // const handleSubmit = (event) => {
  //   event.preventDefault();
  //   setOpen(false);
  //   const data = new FormData(event.currentTarget);

  //   let firstname = data.get("firstName");
  //   let lastname = data.get("lastName");
  //   let email = data.get("email");
  //   let password = data.get("password");
  //   let dateOfBirth = data.get("dateOfBirth");
  //   let gender = data.get("gender");

  //   const jsonData = {
  //     email: email,
  //     firstName: firstname,
  //     lastName: lastname,
  //     password: password,
  //     dateOfBirth: dateOfBirth,
  //     gender: gender
  //   };

  //   console.log(jsonData);

  //   axios
  //     .post(process.env.REACT_APP_SERVER_URL + "/api/auth/register", jsonData)
  //     .then((res) => {
  //       console.log(res.data);
  //       // Write your logic here after successful response
  //       setOpen(true);
  //       setMessageStatus(true);
  //       localStorage.setItem('token', res.data.token);
  //       navigate('/profile');

  //     })
  //     .catch((err) => {
  //       console.log("Error ", err.response.data.message);
  //       if (!err.response.data.success) {
  //         console.log("unsuccessful registration");
  //         setErrorMessage("Unsuccessful Registration");
  //         setOpen(true);
  //         setMessageStatus(false);
  //       }
  //     });
  // };

  if (!localStorage.getItem('token')) {
    return (
      <Container
        maxWidth="lg"
        sx={{
          flexGrow: 1,
          display: 'flex',
          flexDirection: 'column',
          // justifyContent: 'center',
        }}
      >
        <CssBaseline />
        <Box
          sx={{
            display: "flex",
            flexDirection: "column",
            // alignItems: "center",
          }}
        >
          <Typography component="h1" variant="h5">
            Create new blog
          </Typography>
          <Typography component="p" variant="body2">
            You are not logged in!
          </Typography>
        </Box>
      </Container>
    );
  }

  return (
    <Container
      maxWidth="lg"
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
          // alignItems: "center",
        }}
      >
        <Typography component="h1" variant="h5">
          Create new blog
        </Typography>
        <Box component="form" noValidate sx={{ mt: 3 }}>
          <Grid container spacing={2}>
            <Grid item xs={12}>
              <TextField
                autoComplete="given-name"
                name="title"
                required
                fullWidth
                id="title"
                label="title"
                autoFocus
              />
            </Grid>
            <Grid item xs={12} display='block'>
              <InputLabel id="demo-simple-select-label">Category</InputLabel>
              <Select
                fullWidth
                labelId="demo-simple-select-label"
                id="demo-simple-select"
                label="Category"
                name="category"
                value={category}
                onChange={(e) => { setCategory(e.target.value); }}
              >
                <MenuItem value={1}>Technology</MenuItem>
                <MenuItem value={2}>Science</MenuItem>
              </Select>
            </Grid>
            <Grid item xs={12}>
              <TextField
                autoComplete="given-image"
                name="image"
                required
                width={50}
                type={"file"}
                autoFocus
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                autoComplete="given-text"
                name="content"
                required
                fullWidth
                label="Content"
                multiline
                rows={10}
                autoFocus
              />
            </Grid>
          </Grid>
          <Button
            type="submit"
            fullWidth
            variant="contained"
            sx={{ mt: 3, mb: 2, py: 1.3 }}
          >
            Create
          </Button>

        </Box>
      </Box>
    </Container >
  );
}
