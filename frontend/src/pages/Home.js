import { Box, Container, CssBaseline, Typography } from '@mui/material';
import React from 'react';
import NavBar from '../components/NavBar';

export default function Home() {
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
            Blog list here
          </Box>
        </Container>
      </Box>
    </>
  );
}
