import * as React from 'react';
import Box from '@mui/material/Box';
import Container from '@mui/material/Container';
import Typography from '@mui/material/Typography';

function Footer() {

  return (
    <Box component="footer" sx={ { bgcolor: '#eeeeee', py: 2 } }>
      <Container maxWidth="lg">
        <Typography align="center">
          created by: <b>Ashwani</b>
        </Typography>
      </Container>
    </Box>
  );
}

export default Footer;