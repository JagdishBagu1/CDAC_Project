import { Avatar, Container, Typography } from '@mui/material';
import * as React from 'react';


export default function Profile() {

  return (
    <Container
      maxWidth="xl"
      sx={{
        flexGrow: 1,
      }}
    >
      <h1>Profile:</h1>
      <Avatar sx={{ width: 100, height: 100 }} />
      <Typography mt={2}>
        <strong>Email:</strong> example@gmail.com
      </Typography>
      <Typography mt={2}>
        <strong>First Name:</strong> example
      </Typography>
      <Typography mt={2}>
        <strong>Last Name:</strong> example
      </Typography>
      <Typography mt={2}>
        <strong>Gender:</strong> example
      </Typography>
      <Typography mt={2}>
        <strong>Date of Birth:</strong> dd/mm/yyyy
      </Typography>
    </Container>
  );
}