import { Box, Button, Container, CssBaseline, Typography } from '@mui/material';
import React, { useState, useEffect } from 'react';
import Blog from '../components/Blog';
import Grid from "@mui/material/Grid";
import Chip from "@mui/joy/Chip";
import Paper from '@mui/material/Paper';
import InputBase from '@mui/material/InputBase';
import IconButton from '@mui/material/IconButton';
import SearchIcon from '@mui/icons-material/Search';
import axios from 'axios';

export default function Home() {
  const [blogs, setBlogs] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');
  const [categories, setCategories] = useState([]);
  const [selectedCategoryId, setSelectedCategoryId] = useState('');

  useEffect(() => {
    getAllBlogs();
    getAllCategories();
  }, []);


  // get all categories
  const getAllCategories = () => {
    console.log("in get all categories");

    axios.get(process.env.REACT_APP_SERVER_URL + "/api/categories").then(res => {
      console.log("results ", res.data);
      if (res.data.success) {
        console.log("No error");
        setCategories(res.data.body);
      }
    }).catch(err => {
      console.log("Error ", err.message);
    });
  };


  // get all blogs
  const getAllBlogs = () => {
    console.log("in get all blogs");

    axios.get(process.env.REACT_APP_SERVER_URL + "/api/posts").then(res => {
      console.log("results ", res.data);
      if (res.data.success) {
        console.log("No error");
        setBlogs(res.data.body);
      }
    }).catch(err => {
      console.log("Error ", err.message);
    });
  };

  // search blogs
  const searchBlogs = () => {
    console.log("in search blogs");

    axios.get(process.env.REACT_APP_SERVER_URL + "/api/posts/search/" + searchTerm).then(res => {
      console.log("results ", res.data);
      if (res.data.success) {
        console.log("No error");
        setBlogs(res.data.body);

        setSelectedCategoryId('');
      }
    }).catch(err => {
      console.log("Error ", err.message);
    });
  };

  // get by category
  const handleCategory = (catId) => {
    console.log(" category Id", catId);
    setSearchTerm('');

    axios.get(process.env.REACT_APP_SERVER_URL + "/api/posts/categories/" + catId).then(res => {
      console.log("results ", res.data);
      if (res.data.success) {
        console.log("No error");
        setBlogs(res.data.body);
        setSelectedCategoryId(catId);
      }
    }).catch(err => {
      console.log("Error ", err.message);
    });
  };

  return (
    <>
      <Container maxWidth="xl">
        <Box sx={ {
          my: 2,
          position: 'relative',
          minWidth: '100%',
          height: '250px',
          backgroundImage: 'url(./images/4.jpg)',
          color: '#ffffff',
          backgroundSize: 'cover',
          backgroundRepeat: 'no-repeat',
          backgroundPosition: 'center',
          display: 'flex',
          justifyContent: 'center',
          alignItems: 'center'
        } }>
          <Typography
            variant='h4'
            sx={ {
              width: '50%',
              textAlign: 'center',
              textShadow: '2px 2px 10px black'
            } }
          >
            We are working to become one of the best.
          </Typography>
        </Box>

        <Box>
          {/* search box */ }
          <Paper
            variant='outlined'
            component="form"
            sx={ { p: '2px 4px', display: 'flex', alignItems: 'center', width: 300 } }
          >
            <InputBase
              sx={ { ml: 1, flex: 1 } }
              placeholder="Search"
              inputProps={ { 'aria-label': 'search' } }
              onChange={ e => setSearchTerm(e.target.value) }
              value={ searchTerm }
            />
            <IconButton type="button" sx={ { p: '10px' } } aria-label="search" onClick={ searchBlogs }>
              <SearchIcon />
            </IconButton>
          </Paper>

          {/* categories list */ }
          <Grid container spacing={ 2 } >
            { categories.map(cat => (
              <Grid item key={ cat.id }>
                <Chip
                  variant={ selectedCategoryId === cat.id ? 'solid' : 'soft' }
                  color='info'
                  sx={ {
                    fontWeight: 700,
                  } }
                  onClick={ () => handleCategory(cat.id) }
                >
                  { cat.name }
                </Chip>
              </Grid>
            )) }
          </Grid>

          {/* list of blogs  */ }
          { blogs.map(blog => (
            <Blog key={ blog.id } data={ blog } />
          )) }

        </Box>
      </Container>
    </>
  );
}
