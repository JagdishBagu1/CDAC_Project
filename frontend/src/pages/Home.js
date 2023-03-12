import { Box, Button, Container, CssBaseline, Typography } from '@mui/material';
import React, { useState } from 'react';
import NavBar from '../components/NavBar';
import Blog from '../components/Blog'
import Grid from "@mui/material/Grid";
import Chip from "@mui/joy/Chip";
import Paper from '@mui/material/Paper';
import InputBase from '@mui/material/InputBase';
import IconButton from '@mui/material/IconButton';
import SearchIcon from '@mui/icons-material/Search';

export default function Home() {

  const handleCategory = () => {
    console.log("clicked category")

  }

  return (
    <>
      <CssBaseline />
      <NavBar />
      <Box component="main" sx={{
        minHeight: '100vh'
      }}>
        <Container maxWidth="xl">
          <Box sx={{
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
          }}>
            <Typography variant='h3' sx={{
              width: '50%',
              textAlign: 'center',
              textShadow: '2px 2px 10px black'
            }}>We are working to become one of the best.</Typography>
          </Box>
          <Box sx={{
            borderTop: '2px solid #cccccc'
          }}>
            {/* blog section */}
            <Container sx={{mt:4}}>

              {/* search box */}
              <Paper
                component="form"
                sx={{ p: '2px 4px', mt:2, display: 'flex', alignItems: 'center', width: 400 }}
              >
                <InputBase
                  sx={{ ml: 1, flex: 1 }}
                  placeholder="Search for blogs"
                  inputProps={{ 'aria-label': 'search google maps' }}
                />
                <IconButton type="button" sx={{ p: '10px' }} aria-label="search">
                  <SearchIcon />
                </IconButton>

              </Paper>

              {/* categories list */}
              <Grid container spacing={3} sx={{my:1}}>
                <Grid item xs={6} sm={3} md={2} lg={1} sx={{ mx: 1 }}>
                  <Chip
                    size="sm"
                    variant="soft"
                    color='info'
                    sx={{ fontWeight: 700 }}
                    onClick={handleCategory}
                  >
                    Technology
                  </Chip>
                </Grid>
                <Grid item xs={6} sm={3} md={2} lg={1} sx={{ mx: 1 }}>
                  <Chip
                    size="sm"
                    variant="soft"
                    color='info'
                    sx={{ fontWeight: 700}}
                  >
                    Development
                  </Chip>
                </Grid>
                <Grid item xs={6} sm={3} md={2} lg={1} sx={{ mx: 1 }}>
                  <Chip
                    size="sm"
                    color='info'
                    variant="soft"
                    sx={{ fontWeight: 700 }}
                  >
                    Government
                  </Chip>
                </Grid>
                <Grid item xs={6} sm={3} md={2} lg={1} sx={{ mx: 1 }}>
                  <Chip
                    size="sm"
                    color='info'
                    variant="soft"
                    sx={{ fontWeight: 700}}
                  >
                    Community
                  </Chip>
                </Grid>
              </Grid>
              {/* list of blogs  */}

              {[1,2,3].map(element => (
              <Blog />
              ))}
            </Container>
          </Box>
        </Container>
      </Box>
    </>
  );
}
