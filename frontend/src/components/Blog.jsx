import React from "react";

import { Container, Typography } from "@mui/material";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import CardMedia from "@mui/material/CardMedia";
import Grid from "@mui/material/Grid";
import { Avatar } from "@mui/material";
import { deepOrange, deepPurple } from "@mui/material/colors";
import Chip from "@mui/joy/Chip";

function Blog() {
  return (
      <Card sx={{my:2}}>
        <Grid container>
          <Grid item xs={12} sm={8} lg={9} md={9}>
            <CardContent>
              <Typography variant="caption" component={"span"} sx={{ my: 2 }}>
                <Avatar
                  sx={{
                    bgcolor: deepOrange[500],
                    width: 25,
                    height: 25,
                    fontSize: 12,
                    display: "inline-grid",
                    mr: 1,
                  }}
                >
                  SS
                </Avatar>
                Shivam Sharma
              </Typography>
              <Typography gutterBottom variant="h6" component="div">
                Blog Title 1
              </Typography>
              <Typography variant="body2" color="text.secondary">
                Lizards are a widespread group of squamate reptiles, with over
                6,000 species, ranging across all continents except Antarctica
              </Typography>
              <Typography
                variant="body2"
                display="inline"
                sx={{ fontWeight: 600, color: "#818181" }}
              >
                Mar 22 2023
              </Typography>
              <Typography display="inline">
                <Chip
                  size="sm"
                  variant="soft"
                  sx={{ px: 1.5, fontWeight: 700, ml: 5 }}
                >
                  Categories
                </Chip>
              </Typography>
            </CardContent>
          </Grid>
          <Grid item xs={12} sm={4} lg={3} md={3}>
            <CardMedia
              component="img"
              height="160"
              image="https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg"
              alt="green iguana"
            />
          </Grid>
        </Grid>
      </Card>
  );
}

export default Blog;
