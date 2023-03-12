import React from 'react'
import { useParams } from 'react-router-dom'

function BlogDetail() {
  const {id} = useParams()
  // get blog detail
  const getBlogDetails = () => {
    
  }

  return (
    <div>
      {id}
    </div>
  )
}

export default BlogDetail