import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";
import { Typography } from "@mui/material";
import { Container } from "@mui/system";
import { Avatar } from "@mui/material";
import { deepOrange } from "@mui/material/colors";
import dateFormat from "dateformat";

function BlogDetail() {
  const { id } = useParams();
  const [data, setData] = useState({});

  useEffect(() => {
    getBlogDetails();
  }, []);

  // get blog detail
  const getBlogDetails = () => {
    axios
      .get(process.env.REACT_APP_SERVER_URL + "/api/posts/" + id)
      .then((res) => {
        console.log(res.data);
        if (res.data.success) {
          setData(res.data.body);
        } else {
          console.log("error");
        }
      })
      .catch((err) => {
        console.log("Error ", err);
      });
  };

  return (
    <Container sx={ { mt: 3 } }>
      <Typography
        variant="h4"
        color={ "secondary" }
        sx={ { textAlign: "center", mb: 3, fontWeight: 600 } }
      >
        Blog Details
      </Typography>
      <Typography variant="caption" component={ "span" } sx={ { my: 2, fontSize: 16 } }>
        <Avatar
          sx={ {
            bgcolor: deepOrange[500],
            width: 40,
            height: 40,
            fontSize: 20,
            display: "inline-grid",
            mr: 1,
            textTransform: "uppercase",
          } }
        >
          { data.user && data.user.firstName[0] + data.user.lastName[0] }
        </Avatar>
        { data.user && data.user.firstName.toUpperCase() }{ " " }
        { data.user && data.user.lastName.toUpperCase() }
      </Typography>
      <Typography variant="h5" sx={ { mt: 2 } }>{ data.title }</Typography>

      <Typography
        variant="body2"
        component={ "div" }
        sx={ { fontWeight: 600, color: "#818181" } }
      >
        {/* {data.updatedAt} */ }
        { data && dateFormat(data.updatedAt, "mmm d, yyyy") }
      </Typography>

      <img
        src={ `${process.env.REACT_APP_IMAGE_SERVER_URL}/${data.imageUrl}` }
        alt={ data.title }
        width="100%"
        loading="lazy"
      />
      <Typography variant="subtitle1" sx={ { my: 3 } }>{ data.content }</Typography>
    </Container>
  );
}

export default BlogDetail;
