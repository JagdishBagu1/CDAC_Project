import { Avatar, Button, Container, Grid, Typography } from '@mui/material';
import axios from 'axios';
import * as React from 'react';
import { useState } from 'react';
import { useEffect } from 'react';
import { Link } from 'react-router-dom';
import Blog from '../components/Blog';
import NavBar from '../components/NavBar';


export default function Profile() {
  const [posts, setPosts] = useState([]);

  useEffect(() => {
    getAllBlogsOfThisuser();
  }, []);

  const getAllBlogsOfThisuser = () => {
    console.log("in get all blogs of this user");

    if (!localStorage.getItem('token')) return;

    const token = localStorage.getItem("token");

    axios.get(`${process.env.REACT_APP_SERVER_URL}/api/posts/users/${user.id}`, {
      headers: { Authorization: `Bearer ${token}` },
    }).then(res => {
      console.log("results ", res.data);
      if (res.data.success) {
        console.log("No error");
        setPosts(res.data.body);
      }
    }).catch(err => {
      console.log("Error ", err.message);
    });

  };

  if (!localStorage.getItem('token')) {
    return (
      <>
        <NavBar title={"BLOGGING APPLICATION"} />
        <Container
          maxWidth="xl"
          sx={{
            flexGrow: 1,
          }}
        >
          <h1>Profile:</h1>
          <Typography mt={2}>You are not logged in!</Typography>
        </Container>
      </>
    );
  }

  const user = JSON.parse(localStorage.getItem('user'));

  return (
    <>
      <NavBar title={"BLOGGING APPLICATION"} />
      <Container
        maxWidth="xl"
        sx={{
          flexGrow: 1,
        }}
      >
        <Grid container spacing={2}>
          <Grid item xs={12} md={3}>
            <h1>Profile:</h1>
            <Avatar sx={{ width: 100, height: 100 }} />
            <Typography mt={2}>
              <strong>Email:</strong> {user.email}
            </Typography>
            <Typography mt={2}>
              <strong>First Name:</strong> {user.firstName}
            </Typography>
            <Typography mt={2}>
              <strong>Last Name:</strong> {user.lastName}
            </Typography>
            <Typography mt={2}>
              <strong>Gender:</strong> {user.gender}
            </Typography>
            <Typography mt={2}>
              <strong>Date of Birth:</strong> {user.dateOfBirth}
            </Typography>

            <Button variant='contained' sx={{ my: 2, color: 'white' }} component={Link} to='/addBlog'>New blog</Button>
          </Grid>
          <Grid item xs={12} md={9}>
            <h1>Blogs List:</h1>
            {/* all the blogs here */}
            {posts.map(blog => (
              <Blog key={blog.id} data={blog} />
            ))}
          </Grid>
        </Grid>
      </Container>
    </>
  );
}