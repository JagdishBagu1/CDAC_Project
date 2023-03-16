import { Alert, Box, Button, Container, CssBaseline, Grid, InputLabel, MenuItem, Select, Snackbar, TextField, Typography } from "@mui/material";
import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function AddBlog() {
  const [open, setOpen] = useState(false);
  const [messageStatus, setMessageStatus] = useState(true);
  const [errorMessage, setErrorMessage] = useState('');
  const [category, setCategory] = useState('');

  const navigate = useNavigate();

  const handleClose = () => {
    setOpen(false);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    setOpen(false);
    const data = new FormData(event.currentTarget);
    const user = JSON.parse(localStorage.getItem('user'));
    const token = localStorage.getItem("token");

    let title = data.get("title");
    let category = data.get("category");
    let image = data.get("image");
    let content = data.get("content");

    const jsonData = {
      image,
      postData: JSON.stringify({
        title,
        content
      })
    };

    console.log(`${process.env.REACT_APP_SERVER_URL}/api/posts/users/${user.id}/categories/${category}`);
    console.log(jsonData);

    axios
      .post(`${process.env.REACT_APP_SERVER_URL}/api/posts/users/${user.id}/categories/${category}`, jsonData, {
        headers: { Authorization: `Bearer ${token}`, "Content-Type": "multipart/form-data" },
      })
      .then((res) => {
        console.log(res.data);
        // Write your logic here after successful response
        setOpen(true);
        setMessageStatus(true);
        navigate('/profile');

      })
      .catch((err) => {
        console.log("Error ", err.response.data.message);
        if (!err.response.data.success) {
          console.log("unsuccessful blog creation");
          setErrorMessage("unsuccessful blog creation");
          setOpen(true);
          setMessageStatus(false);
        }
      });
  };

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
        <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 3 }}>
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
                <MenuItem value={3}>Gaming</MenuItem>
                <MenuItem value={4}>Sports</MenuItem>
                <MenuItem value={5}>Finance</MenuItem>
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
