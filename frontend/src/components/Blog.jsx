import React from "react";

import { capitalize, Container, Typography } from "@mui/material";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import CardMedia from "@mui/material/CardMedia";
import Grid from "@mui/material/Grid";
import { Avatar } from "@mui/material";
import { deepOrange, deepPurple } from "@mui/material/colors";
import Chip from "@mui/joy/Chip";
import dateFormat from 'dateformat';
import { Link } from "react-router-dom";

function Blog({ data }) {
  return (
    <Card sx={ { my: 2 } }>
      <Grid container>
        <Grid item xs={ 12 } sm={ 12 } md={ 4 } lg={ 3 }>
          <CardMedia
            component="img"
            height="170"
            image={ `${process.env.REACT_APP_IMAGE_SERVER_URL}/${data.imageUrl}` }
            alt="green iguana"
          />
        </Grid>
        <Grid item xs={ 12 } sm={ 8 } md={ 8 } lg={ 9 }>
          <CardContent>
            <Typography variant="caption" component={ "span" } sx={ { my: 2 } }>
              <Avatar
                sx={ {
                  bgcolor: deepOrange[500],
                  width: 25,
                  height: 25,
                  fontSize: 12,
                  display: "inline-grid",
                  mr: 1,
                  textTransform: 'uppercase'
                } }
              >
                { data.user.firstName[0] }{ data.user.lastName[0] }
              </Avatar>
              { data.user.firstName.toUpperCase() } { data.user.lastName.toUpperCase() }
            </Typography>
            <br />
            <Typography gutterBottom variant="h6" sx={ { textTransform: 'capitalize', textDecoration: 'none' } } component={ Link } to={ `/blogDetails/${data.id}` } >
              { data.title }
            </Typography>
            <Typography variant="body2" color="text.secondary">
              { data.content.substring(0, 200) + "..." }
            </Typography>
            <Typography
              variant="body2"
              display="inline-block"
              sx={ { fontWeight: 600, color: "#818181" } }
            >
              {/* {data.updatedAt} */ }
              { dateFormat(data.updatedAt, "mmm d, yyyy, hh:ss") }
            </Typography>
            <Typography display="inline" variant="div">
              <Chip
                size="sm"
                variant="soft"
                sx={ { px: 1.5, fontWeight: 700, ml: 1 } }
              >
                { data.category.name }
              </Chip>
            </Typography>
          </CardContent>
        </Grid>

      </Grid>
    </Card>
  );
}

export default Blog;
